package tv.codely.shared.infrastructure.dependency_injection

import scala.concurrent.ExecutionContext

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import tv.codely.shared.domain.bus.MessagePublisher
import tv.codely.shared.domain.logger.Logger
import tv.codely.shared.infrastructure.bus.rabbitmq.{RabbitMqChannelFactory, RabbitMqConfig, RabbitMqMessagePublisher}
import tv.codely.shared.infrastructure.doobie.{DoobieDbConnection, JdbcConfig}
import tv.codely.shared.infrastructure.logger.ScalaLoggingLogger

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
