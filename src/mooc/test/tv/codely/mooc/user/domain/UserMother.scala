package tv.codely.mooc.user.domain

import tv.codely.shared.domain.SeqMother

object UserMother {
  def apply(
      id: String = UserIdMother.random.value.toString,
      name: String = UserNameMother.random.value
  ): User = User(id, name)

  def random: User = apply()

  def randomSeq: Seq[User] = SeqMother.randomOf(apply())
}
