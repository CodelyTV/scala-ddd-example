package tv.codely.scala_http_api.application.stubs.user

import tv.codely.scala_http_api.application.user.api.User
import tv.codely.scala_http_api.application.stubs.SeqStub

object UserStub {
  def apply(
      id: String = UserIdStub.random.value.toString,
      name: String = UserNameStub.random.value
  ): User = User(id, name)

  def random: User = apply()

  def randomSeq: Seq[User] = SeqStub.randomOf(apply())
}
