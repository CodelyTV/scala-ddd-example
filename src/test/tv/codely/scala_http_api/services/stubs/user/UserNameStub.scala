package tv.codely.scala_http_api.application.stubs.user

import tv.codely.scala_http_api.application.stubs.{IntStub, StringStub}
import tv.codely.scala_http_api.application.user.api.UserName

object UserNameStub {
  private val minimumChars = 1
  private val maximumChars = 20

  def apply(value: String): UserName = UserName(value)

  def random: UserName = UserName(
    StringStub.random(numChars = IntStub.randomBetween(minimumChars, maximumChars))
  )
}
