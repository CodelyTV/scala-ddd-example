package tv.codely.scala_http_api.infrastructure.stubs

import java.util.UUID

import tv.codely.scala_http_api.domain.UserId

object UserIdStub {
  private val userIdLength = 3

  def apply(value: String): UserId = UserIdStub(UUID.fromString(value))

  def apply(value: UUID): UserId = UserId(value)

  def random: UserId = UserId(StringStub.random(numChars = userIdLength))
}
