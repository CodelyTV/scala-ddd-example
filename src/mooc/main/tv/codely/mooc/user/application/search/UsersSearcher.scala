package tv.codely.mooc.user.application.search

import tv.codely.mooc.user.domain.{User, UserRepository}

import scala.concurrent.Future

final class UsersSearcher(repository: UserRepository) {
  def all(): Future[Seq[User]] = repository.all()
}
