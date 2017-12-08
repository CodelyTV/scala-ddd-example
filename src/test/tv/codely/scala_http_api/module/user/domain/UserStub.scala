package tv.codely.scala_http_api.module.user.domain

import tv.codely.scala_http_api.module.shared.domain.IntStub

object UserStub {
  def apply(
      id: String = UserIdStub.random.value.toString,
      name: String = UserNameStub.random.value
  ): User = User(id, name)

  def random: User = apply()

  def randomSeq: Seq[User] = (0 to IntStub.randomBetween(1, 20)).map(_ => apply())
}
