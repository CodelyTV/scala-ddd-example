package tv.codely.scala_http_api.module.user.domain

import tv.codely.scala_http_api.module.shared.bus.domain.Message
import tv.codely.scala_http_api.module.shared.user.domain.UserId

object UserRegistered {
  def apply(id: String, name: String): UserRegistered = apply(UserId(id), UserName(name))

  def apply(user: User): UserRegistered = apply(user.id, user.name)
}

final case class UserRegistered(id: UserId, name: UserName) extends Message {
  override val subType: String = "user_registered"
}
