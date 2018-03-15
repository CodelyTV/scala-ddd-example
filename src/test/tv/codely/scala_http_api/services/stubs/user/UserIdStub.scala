package tv.codely.scala_http_api.application.stubs.user

import java.util.UUID

import tv.codely.scala_http_api.application.stubs.UuidStub
import tv.codely.scala_http_api.application.user.api.UserId

object UserIdStub {
  def apply(value: String): UserId = UserIdStub(UuidStub(value))

  def apply(value: UUID): UserId = UserId(value)

  def random: UserId = UserId(UuidStub.random)
}
