package tv.codely.scala_http_api.module.user.domain

import tv.codely.scala_http_api.module.shared.user.domain.UserId

object UserRegisteredStub {
  def apply(
      id: UserId = UserIdStub.random,
      name: UserName = UserNameStub.random
  ): UserRegistered = UserRegistered(id, name)

  def apply(user: User): UserRegistered = apply(user.id, user.name)

  def random: UserRegistered = apply()
}
