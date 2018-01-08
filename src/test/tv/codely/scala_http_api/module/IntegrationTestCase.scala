package tv.codely.scala_http_api.module

import com.typesafe.config.ConfigFactory
import tv.codely.scala_http_api.module.shared.domain.MessagePublisher
import tv.codely.scala_http_api.module.shared.infrastructure.config.{DbConfig, MessageBrokerConfig}
import tv.codely.scala_http_api.module.shared.infrastructure.dependency_injection.SharedModuleDependencyContainer
import tv.codely.scala_http_api.module.shared.infrastructure.message_broker.rabbitmq.{MessageConsumer, RabbitMqMessageConsumer}
import tv.codely.scala_http_api.module.shared.infrastructure.persistence.doobie.DoobieDbConnection

import scala.concurrent.ExecutionContext

protected[scala_http_api] trait IntegrationTestCase extends UnitTestCase {
  private val actorSystemName = "scala-http-api-integration-test"

  private val appConfig       = ConfigFactory.load("application")
  private val dbConfig        = DbConfig(appConfig.getConfig("database"))
  private val publisherConfig = MessageBrokerConfig(appConfig.getConfig("message-publisher"))

  private val sharedDependencies = new SharedModuleDependencyContainer(actorSystemName, dbConfig, publisherConfig)

  implicit protected val executionContext: ExecutionContext = sharedDependencies.executionContext

  protected val doobieDbConnection: DoobieDbConnection = sharedDependencies.doobieDbConnection
  protected val messagePublisher: MessagePublisher     = sharedDependencies.messagePublisher

  protected val videoCreatedQueueConsumer: MessageConsumer =
    new RabbitMqMessageConsumer(publisherConfig)("codelytv_scala_api.video_created")
}
