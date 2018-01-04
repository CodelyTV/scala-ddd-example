package tv.codely.scala_http_api.module.user.domain

import tv.codely.scala_http_api.module.video.domain.SeqStub

object UserStub {
  def apply(
      id: String = UserIdStub.random.value.toString,
      name: String = UserNameStub.random.value
  ): User = User(id, name)

  def random: User = apply()

  def randomSeq: Seq[User] = SeqStub.randomOf(apply())
}
