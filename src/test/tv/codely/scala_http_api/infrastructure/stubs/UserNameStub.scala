package tv.codely.scala_http_api.infrastructure.stubs

import tv.codely.scala_http_api.domain.UserName

object UserNameStub {
  private val userNameMinimumChars = 1
  private val userNameMaximumChars = 20

  def apply(value: String): UserName = UserName(value)

  def random: UserName = UserName(
    StringStub.random(numChars = IntStub.randomBetween(userNameMinimumChars, userNameMaximumChars))
  )
}
