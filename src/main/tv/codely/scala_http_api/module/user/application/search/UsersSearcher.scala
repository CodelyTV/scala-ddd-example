package tv.codely.scala_http_api.module.user.application.search

import tv.codely.scala_http_api.module.user.domain.{User, UserRepository}

final class UsersSearcher(repository: UserRepository) {
  def all(): Seq[User] = repository.all()
}
