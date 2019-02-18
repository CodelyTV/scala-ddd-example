package tv.codely.mooc.user.domain

import tv.codely.mooc.shared.domain.user.UserId

object User {
  def apply(id: String, name: String): User = User(UserId(id), UserName(name))
}

case class User(id: UserId, name: UserName)
