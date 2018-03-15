package tv.codely.scala_http_api.effects.repositories.api

import tv.codely.scala_http_api.application.user.api.User

trait UserRepository[P[_]] {
  def all(): P[Seq[User]]

  def save(user: User): P[Unit]
}

object UserRepository{
  import cats.~>

  implicit def toQ[P[_], Q[_]](implicit 
    P: UserRepository[P],
    nat: P ~> Q) = new UserRepository[Q]{
      def all(): Q[Seq[User]] = nat(P.all())
      def save(user: User): Q[Unit] = nat(P.save(user))
  }
}
