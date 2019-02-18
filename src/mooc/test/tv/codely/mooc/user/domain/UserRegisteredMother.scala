package tv.codely.mooc.user.domain

import tv.codely.mooc.shared.domain.user.UserId

object UserRegisteredMother {
  def apply(
      id: UserId = UserIdMother.random,
      name: UserName = UserNameMother.random
  ): UserRegistered = UserRegistered(id, name)

  def apply(user: User): UserRegistered = apply(user.id, user.name)

  def random: UserRegistered = apply()
}
