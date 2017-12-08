package tv.codely.scala_http_api.module.shared.infrastructure.dependency_injection

import tv.codely.scala_http_api.module.shared.infrastructure.config.DbConfig
import tv.codely.scala_http_api.module.shared.infrastructure.persistence.doobie.DoobieDbConnection

final class SharedModuleDependencyContainer(dbConfig: DbConfig) {
  val doobieDbConnection = new DoobieDbConnection(dbConfig)
}
