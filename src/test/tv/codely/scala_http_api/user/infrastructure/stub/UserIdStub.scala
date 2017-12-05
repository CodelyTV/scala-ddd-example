package tv.codely.scala_http_api.user.infrastructure.stubs

import java.util.UUID

import tv.codely.scala_http_api.user.domain.UserId
import tv.codely.scala_http_api.shared.infrastructure.stub.StringStub

object UserIdStub {
  private val userIdLength = 3

  def apply(value: String): UserId = UserIdStub(UUID.fromString(value))

  def apply(value: UUID): UserId = UserId(value)

  def random: UserId = UserId(StringStub.random(numChars = userIdLength))
}
