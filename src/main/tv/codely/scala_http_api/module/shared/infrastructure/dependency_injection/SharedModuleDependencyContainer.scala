package tv.codely.scala_http_api.module.shared.infrastructure.dependency_injection

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import tv.codely.scala_http_api.module.shared.infrastructure.config.DbConfig
import tv.codely.scala_http_api.module.shared.infrastructure.persistence.doobie.DoobieDbConnection

import scala.concurrent.ExecutionContext

final class SharedModuleDependencyContainer(actorSystemName: String, dbConfig: DbConfig) {
  implicit val actorSystem: ActorSystem  = ActorSystem(actorSystemName)
  val materializer: ActorMaterializer    = ActorMaterializer()
  val executionContext: ExecutionContext = actorSystem.dispatcher

  val doobieDbConnection: DoobieDbConnection = new DoobieDbConnection(dbConfig)
}
