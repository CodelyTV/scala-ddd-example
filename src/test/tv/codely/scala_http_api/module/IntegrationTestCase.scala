package tv.codely.scala_http_api.module

import com.typesafe.config.ConfigFactory
import tv.codely.scala_http_api.module.shared.bus.domain.MessagePublisher
import tv.codely.scala_http_api.module.shared.bus.infrastructure.rabbit_mq.{RabbitMqChannelFactory, RabbitMqConfig}
import tv.codely.scala_http_api.module.shared.dependency_injection.infrastructure.SharedModuleDependencyContainer
import tv.codely.scala_http_api.module.shared.logger.domain.Logger
import tv.codely.scala_http_api.module.shared.persistence.infrastructure.doobie.{DoobieDbConnection, JdbcConfig}

import scala.concurrent.ExecutionContext

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
