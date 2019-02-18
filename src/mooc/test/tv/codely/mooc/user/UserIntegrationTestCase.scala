package tv.codely.mooc.user

import tv.codely.mooc.user.domain.UserRepository
import tv.codely.mooc.user.infrastructure.dependency_injection.UserModuleDependencyContainer
import tv.codely.shared.infrastructure.integration.IntegrationTestCase

protected[user] trait UserIntegrationTestCase extends IntegrationTestCase {
  private val container = new UserModuleDependencyContainer(doobieDbConnection, messagePublisher)

  protected val repository: UserRepository = container.repository
}
