package tv.codely.scala_http_api.module

import com.typesafe.config.ConfigFactory
import tv.codely.scala_http_api.module.shared.bus.infrastructure.rabbit_mq.RabbitMqConfig
import tv.codely.shared.infrastructure.doobie.{DoobieDbConnection, JdbcConfig}
import scala.concurrent.ExecutionContext

import tv.codely.shared.domain.bus.MessagePublisher
import tv.codely.shared.domain.logger.Logger
import tv.codely.shared.infrastructure.bus.rabbitmq.RabbitMqChannelFactory
import tv.codely.shared.infrastructure.dependency_injection.SharedModuleDependencyContainer

protected[scala_http_api] trait IntegrationTestCase extends UnitTestCase {
  private val actorSystemName = "scala-http-api-integration-test"

  private val appConfig       = ConfigFactory.load("application")
  private val dbConfig        = JdbcConfig(appConfig.getConfig("database"))
  private val publisherConfig = RabbitMqConfig(appConfig.getConfig("message-publisher"))

  private val sharedDependencies = new SharedModuleDependencyContainer(actorSystemName, dbConfig, publisherConfig)

  implicit protected val executionContext: ExecutionContext = sharedDependencies.executionContext

  protected val doobieDbConnection: DoobieDbConnection         = sharedDependencies.doobieDbConnection
  protected val rabbitMqChannelFactory: RabbitMqChannelFactory = new RabbitMqChannelFactory(publisherConfig)
  protected val messagePublisher: MessagePublisher             = sharedDependencies.messagePublisher
  protected val logger: Logger                                 = sharedDependencies.logger
}
