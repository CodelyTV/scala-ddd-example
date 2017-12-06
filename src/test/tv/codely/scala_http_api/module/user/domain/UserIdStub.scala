package tv.codely.scala_http_api.module.user.domain

import java.util.UUID

import tv.codely.scala_http_api.module.shared.domain.UuidStub

object UserIdStub {
  def apply(value: String): UserId = UserIdStub(UuidStub(value))

  def apply(value: UUID): UserId = UserId(value)

  def random: UserId = UserId(UuidStub.random)
}
