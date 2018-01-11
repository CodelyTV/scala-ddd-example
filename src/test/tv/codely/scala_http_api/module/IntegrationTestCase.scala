package tv.codely.scala_http_api.module

import com.typesafe.config.ConfigFactory
import tv.codely.scala_http_api.module.shared.infrastructure.config.DbConfig
import tv.codely.scala_http_api.module.shared.infrastructure.dependency_injection.SharedModuleDependencyContainer
import tv.codely.scala_http_api.module.shared.infrastructure.persistence.doobie.DoobieDbConnection

import scala.concurrent.ExecutionContext

protected[scala_http_api] trait IntegrationTestCase extends UnitTestCase {
  private val appConfig       = ConfigFactory.load("application")
  private val dbConfig        = DbConfig(appConfig.getConfig("database"))
  private val actorSystemName = "scala-http-api-integration-test"

  protected val sharedDependencies                          = new SharedModuleDependencyContainer(actorSystemName, dbConfig)
  implicit protected val executionContext: ExecutionContext = sharedDependencies.executionContext

  protected val doobieDbConnection: DoobieDbConnection = sharedDependencies.doobieDbConnection
}
