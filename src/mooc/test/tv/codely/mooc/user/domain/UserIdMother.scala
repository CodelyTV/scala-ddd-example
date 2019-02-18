package tv.codely.mooc.user.domain

import java.util.UUID

import tv.codely.mooc.shared.domain.user.UserId
import tv.codely.shared.domain.UuidMother

object UserIdMother {
  def apply(value: String): UserId = UserIdMother(UuidMother(value))

  def apply(value: UUID): UserId = UserId(value)

  def random: UserId = UserId(UuidMother.random)
}
