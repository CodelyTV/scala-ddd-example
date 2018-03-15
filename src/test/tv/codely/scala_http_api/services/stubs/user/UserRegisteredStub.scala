package tv.codely.scala_http_api.application.stubs.user

import tv.codely.scala_http_api.application.user.api.User
import tv.codely.scala_http_api.application.user.api.UserId
import tv.codely.scala_http_api.application.user.api.UserName
import tv.codely.scala_http_api.application.user.api.UserRegistered

object UserRegisteredStub {
  def apply(
      id: UserId = UserIdStub.random,
      name: UserName = UserNameStub.random
  ): UserRegistered = UserRegistered(id, name)

  def apply(user: User): UserRegistered = apply(user.id, user.name)

  def random: UserRegistered = apply()
}
