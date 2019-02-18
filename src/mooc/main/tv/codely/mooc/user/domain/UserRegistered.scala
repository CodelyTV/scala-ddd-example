package tv.codely.mooc.user.domain

import tv.codely.mooc.shared.domain.user.UserId
import tv.codely.shared.domain.bus.Message

object UserRegistered {
  def apply(id: String, name: String): UserRegistered = apply(UserId(id), UserName(name))

  def apply(user: User): UserRegistered = apply(user.id, user.name)
}

final case class UserRegistered(id: UserId, name: UserName) extends Message {
  override val subType: String = "user_registered"
}
