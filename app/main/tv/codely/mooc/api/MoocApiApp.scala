package tv.codely.mooc.api

import scala.concurrent.ExecutionContext
import scala.io.StdIn
import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.stream.ActorMaterializer
import com.typesafe.config.ConfigFactory
import tv.codely.mooc.podcast.infrastructure.dependency_injection.PodcastModuleDependencyContainer
import tv.codely.mooc.user.infrastructure.dependency_injection.UserModuleDependencyContainer
import tv.codely.mooc.video.infrastructure.dependency_injection.VideoModuleDependencyContainer
import tv.codely.shared.infrastructure.bus.rabbitmq.RabbitMqConfig
import tv.codely.shared.infrastructure.dependency_injection.SharedModuleDependencyContainer
import tv.codely.shared.infrastructure.doobie.JdbcConfig

object MoocApiApp {
  def start(): Unit = {
    val appConfig    = ConfigFactory.load("application")
    val serverConfig = ConfigFactory.load("http-server")

    val actorSystemName = appConfig.getString("main-actor-system.name")
    val host            = serverConfig.getString("http-server.host")
    val port            = serverConfig.getInt("http-server.port")

    val dbConfig        = JdbcConfig(appConfig.getConfig("database"))
    val publisherConfig = RabbitMqConfig(appConfig.getConfig("message-publisher"))

    val sharedDependencies = new SharedModuleDependencyContainer(actorSystemName, dbConfig, publisherConfig)

    implicit val system: ActorSystem                = sharedDependencies.actorSystem
    implicit val materializer: ActorMaterializer    = sharedDependencies.materializer
    implicit val executionContext: ExecutionContext = sharedDependencies.executionContext

    val container = new EntryPointDependencyContainer(
      new UserModuleDependencyContainer(sharedDependencies.doobieDbConnection, sharedDependencies.messagePublisher),
      new VideoModuleDependencyContainer(sharedDependencies.doobieDbConnection, sharedDependencies.messagePublisher),
      new PodcastModuleDependencyContainer(sharedDependencies.doobieDbConnection, sharedDependencies.messagePublisher)
    )

    val routes = new Routes(container)

    val bindingFuture = Http().bindAndHandle(routes.all, host, port)

    bindingFuture.failed.foreach { t =>
      println(s"Failed to bind to http://$host:$port/:")
      pprint.log(t)
    }

    // let it run until user presses return
    println(s"Server online at http://$host:$port/\nPress RETURN to stop...")

    StdIn.readLine()

    println("Stopping server...")

    bindingFuture
      .flatMap(_.unbind())
      .onComplete(_ => {
        sharedDependencies.actorSystem.terminate()

        println("Server stopped!")
      })
  }
}