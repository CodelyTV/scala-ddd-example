package tv.codely.mooc.user.infrastructure.dependency_injection

import tv.codely.shared.infrastructure.doobie.DoobieDbConnection
import tv.codely.mooc.user.application.register.UserRegistrar
import tv.codely.mooc.user.application.search.UsersSearcher
import tv.codely.mooc.user.domain.UserRepository
import tv.codely.mooc.user.infrastructure.repository.DoobieMySqlUserRepository
import scala.concurrent.ExecutionContext

import tv.codely.shared.domain.bus.MessagePublisher

final class UserModuleDependencyContainer(
    doobieDbConnection: DoobieDbConnection,
    messagePublisher: MessagePublisher
)(implicit executionContext: ExecutionContext) {
  val repository: UserRepository = new DoobieMySqlUserRepository(doobieDbConnection)

  val usersSearcher: UsersSearcher = new UsersSearcher(repository)
  val userRegistrar: UserRegistrar = new UserRegistrar(repository, messagePublisher)
}
