package tv.codely.scala_http_api.infrastructure.stubs

object UserNameStub {
  private val userNameMinimumChars = 1
  private val userNameMaximumChars = 20

  def apply(value: String): String = value

  def random: String = StringStub.random(numChars = IntStub.randomBetween(userNameMinimumChars, userNameMaximumChars))
}
