package tv.codely.scala_http_api.module.user.infrastructure.stub

import tv.codely.scala_http_api.module.user.domain.User

object UserStub {
  def apply(
      id: String = UserIdStub.random.value.toString,
      name: String = UserNameStub.random.value
  ): User = User(id, name)

  def random: User = apply()
}
