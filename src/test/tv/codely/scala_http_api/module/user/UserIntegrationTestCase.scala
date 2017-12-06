package tv.codely.scala_http_api.module.user

import tv.codely.scala_http_api.module.IntegrationTestCase
import tv.codely.scala_http_api.module.user.infrastructure.dependency_injection.UserModuleDependencyContainer
import tv.codely.scala_http_api.module.user.infrastructure.repository.InMemoryUserRepository

protected[user] trait UserIntegrationTestCase extends IntegrationTestCase {
  private val container = new UserModuleDependencyContainer

  protected val repository: InMemoryUserRepository = container.repository
}
