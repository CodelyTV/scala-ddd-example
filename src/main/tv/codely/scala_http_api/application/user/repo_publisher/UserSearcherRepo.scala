package tv.codely.scala_http_api.application.repo_publisher.user

import tv.codely.scala_http_api.application.user.api.User
import tv.codely.scala_http_api.application.user.api.UsersSearcher
import tv.codely.scala_http_api.effects.repositories.api.UserRepository

final case class UsersSearcherRepo[P[_]]()(implicit 
  repository: UserRepository[P])
extends UsersSearcher[P] {
  def all(): P[Seq[User]] = repository.all()
}