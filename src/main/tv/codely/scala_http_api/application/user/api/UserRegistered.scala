package tv.codely.scala_http_api.application.user.api

import tv.codely.scala_http_api.effects.bus.api.Message

object UserRegistered {
  def apply(id: String, name: String): UserRegistered = apply(UserId(id), UserName(name))

  def apply(user: User): UserRegistered = apply(user.id, user.name)
}

final case class UserRegistered(id: UserId, name: UserName) extends Message {
  override val subType: String = "user_registered"
}
