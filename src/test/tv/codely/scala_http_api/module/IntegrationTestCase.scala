package tv.codely.scala_http_api.module

import com.typesafe.config.ConfigFactory
import tv.codely.scala_http_api.module.shared.infrastructure.config.DbConfig
import tv.codely.scala_http_api.module.shared.infrastructure.dependency_injection.SharedModuleDependencyContainer
import tv.codely.scala_http_api.module.shared.infrastructure.persistence.doobie.DoobieDbConnection

protected[scala_http_api] trait IntegrationTestCase extends UnitTestCase {
  private val appConfig = ConfigFactory.load("application")
  private val dbConfig  = DbConfig(appConfig.getConfig("database"))

  private val sharedDependencies = new SharedModuleDependencyContainer(dbConfig)

  protected val doobieDbConnection: DoobieDbConnection = sharedDependencies.doobieDbConnection
}
