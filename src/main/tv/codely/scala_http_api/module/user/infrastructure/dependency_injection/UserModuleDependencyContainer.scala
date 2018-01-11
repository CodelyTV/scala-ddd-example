package tv.codely.scala_http_api.module.user.infrastructure.dependency_injection

import tv.codely.scala_http_api.module.shared.domain.MessagePublisher
import tv.codely.scala_http_api.module.shared.infrastructure.persistence.doobie.DoobieDbConnection
import tv.codely.scala_http_api.module.user.application.register.UserRegistrar
import tv.codely.scala_http_api.module.user.application.search.UsersSearcher
import tv.codely.scala_http_api.module.user.domain.UserRepository
import tv.codely.scala_http_api.module.user.infrastructure.repository.DoobieMySqlUserRepository

import scala.concurrent.ExecutionContext

final class UserModuleDependencyContainer(
    doobieDbConnection: DoobieDbConnection,
    messagePublisher: MessagePublisher
)(implicit executionContext: ExecutionContext) {
  val repository: UserRepository = new DoobieMySqlUserRepository(doobieDbConnection)

  val usersSearcher: UsersSearcher = new UsersSearcher(repository)
  val userRegistrar: UserRegistrar = new UserRegistrar(repository, messagePublisher)
}
