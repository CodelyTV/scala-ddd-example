package tv.codely.shared.infrastructure.integration

import scala.concurrent.ExecutionContext

import com.typesafe.config.ConfigFactory
import tv.codely.shared.domain.bus.MessagePublisher
import tv.codely.shared.domain.logger.Logger
import tv.codely.shared.infrastructure.bus.rabbitmq.{RabbitMqChannelFactory, RabbitMqConfig}
import tv.codely.shared.infrastructure.dependency_injection.SharedModuleDependencyContainer
import tv.codely.shared.infrastructure.doobie.{DoobieDbConnection, JdbcConfig}
import tv.codely.shared.infrastructure.unit.UnitTestCase

trait IntegrationTestCase extends UnitTestCase {
  private val actorSystemName = "cqrs-ddd-scala-example-integration-test"

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
