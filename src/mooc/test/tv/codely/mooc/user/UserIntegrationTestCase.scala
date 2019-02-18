package tv.codely.scala_http_api.module.user

import tv.codely.scala_http_api.module.IntegrationTestCase
import tv.codely.scala_http_api.module.user.domain.UserRepository
import tv.codely.scala_http_api.module.user.infrastructure.dependency_injection.UserModuleDependencyContainer

protected[user] trait UserIntegrationTestCase extends IntegrationTestCase {
  private val container = new UserModuleDependencyContainer(doobieDbConnection, messagePublisher)

  protected val repository: UserRepository = container.repository
}
