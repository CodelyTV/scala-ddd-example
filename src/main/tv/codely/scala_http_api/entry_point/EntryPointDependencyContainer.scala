package tv.codely.scala_http_api.entry_point

import tv.codely.scala_http_api.entry_point.controller.user.UserGetController
import tv.codely.scala_http_api.module.user.infrastructure.dependency_injection.UserModuleDependencyContainer

final class EntryPointDependencyContainer(userDependencies: UserModuleDependencyContainer) {
  val userGetController = new UserGetController(userDependencies.userSearcher)
}
