package tv.codely.scala_http_api.application.akkaHttp
package client

import akka.actor.ActorSystem
import org.scalatest.{Matchers, WordSpec}
import org.scalatest.concurrent.ScalaFutures
import scala.concurrent.duration._
import tv.codely.scala_http_api.application.stubs.user.UserStub
import tv.codely.scala_http_api.application.stubs.video.VideoStub

class HttpClientSpec extends WordSpec with Matchers with ScalaFutures{
  
  // Read configs

  val httpServerConfig = HttpServerConfig("http://localhost", 8080)
  
  // Inject dependencies

  implicit val actorSystem = ActorSystem("scala-http-api-integration-test")
  implicit val executionContext = actorSystem.dispatcher
  val system = new SystemHttpClient()(httpServerConfig, actorSystem)

  // Run configuration

  "User search should work" ignore {
    assert(system.UsersSearcher.all().isReadyWithin(10.seconds))
  }

  "User register should work" ignore {
    val user = UserStub.random
    assert(system.UserRegister.register(user.id,user.name).isReadyWithin(10.seconds))
  }

  "Video search should work" ignore {
    assert(system.VideosSearcher.all().isReadyWithin(10.seconds))
  }

  "Video creator should work" ignore {
    val user = UserStub.random
    val video = VideoStub.random

    assert(system.VideoCreator.create(
      video.id,
      video.title,
      video.duration,
      video.category,
      user.id).isReadyWithin(10.seconds))
  }
}
