package tv.codely.scala_http_api.user.infrastructure.stubs

import tv.codely.scala_http_api.user.domain.UserName
import tv.codely.scala_http_api.shared.infrastructure.stub.{IntStub, StringStub}

object UserNameStub {
  private val userNameMinimumChars = 1
  private val userNameMaximumChars = 20

  def apply(value: String): UserName = UserName(value)

  def random: UserName = UserName(
    StringStub.random(numChars = IntStub.randomBetween(userNameMinimumChars, userNameMaximumChars))
  )
}
