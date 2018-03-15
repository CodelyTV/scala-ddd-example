package tv.codely.scala_http_api.application.repo_publisher.user

import tv.codely.scala_http_api.application.user.api._
import tv.codely.scala_http_api.effects.bus.api.MessagePublisher
import tv.codely.scala_http_api.application.user.api.UserId
import tv.codely.scala_http_api.effects.repositories.api._
import cats.Apply, cats.syntax.apply._

final case class UserRegisterRepoPublisher[P[_]]()(implicit
  repository: UserRepository[P], 
  publisher: MessagePublisher[P],
  Ap: Apply[P]) 
extends UserRegister[P]{

  def register(id: UserId, name: UserName): P[Unit] = {
    val user = User(id, name)

    repository.save(user) *>
    publisher.publish(UserRegistered(user))
  }
}