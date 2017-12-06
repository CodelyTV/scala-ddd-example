package tv.codely.scala_http_api.module.user.domain

import tv.codely.scala_http_api.module.shared.domain.{IntStub, StringStub}

object UserNameStub {
  private val minimumChars = 1
  private val maximumChars = 20

  def apply(value: String): UserName = UserName(value)

  def random: UserName = UserName(
    StringStub.random(numChars = IntStub.randomBetween(minimumChars, maximumChars))
  )
}
