package tv.codely.scala_http_api.module.user.infrastructure.dependency_injection

import tv.codely.scala_http_api.module.user.application.search.UsersSearcher
import tv.codely.scala_http_api.module.user.infrastructure.repository.InMemoryUserRepository

final class UserModuleDependencyContainer {
  private val repository = new InMemoryUserRepository

  val usersSearcher = new UsersSearcher(repository)
}
