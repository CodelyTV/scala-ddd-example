package tv.codely.scala_http_api.module.shared.dependency_injection.infrastructure

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import tv.codely.scala_http_api.module.shared.bus.domain.MessagePublisher
import tv.codely.scala_http_api.module.shared.bus.infrastructure.rabbit_mq.{RabbitMqChannelFactory, RabbitMqConfig, RabbitMqMessagePublisher}
import tv.codely.scala_http_api.module.shared.logger.domain.Logger
import tv.codely.scala_http_api.module.shared.logger.infrastructure.scala_logging.ScalaLoggingLogger
import tv.codely.scala_http_api.module.shared.persistence.infrastructure.doobie.{DoobieDbConnection, JdbcConfig}

import scala.concurrent.ExecutionContext

final class SharedModuleDependencyContainer(
    actorSystemName: String,
    dbConfig: JdbcConfig,
    publisherConfig: RabbitMqConfig
) {
  implicit val actorSystem: ActorSystem  = ActorSystem(actorSystemName)
  val materializer: ActorMaterializer    = ActorMaterializer()
  val executionContext: ExecutionContext = actorSystem.dispatcher

  val doobieDbConnection: DoobieDbConnection = new DoobieDbConnection(dbConfig)

  private val rabbitMqChannelFactory     = new RabbitMqChannelFactory(publisherConfig)
  val messagePublisher: MessagePublisher = new RabbitMqMessagePublisher(rabbitMqChannelFactory)

  val logger: Logger = new ScalaLoggingLogger
}
