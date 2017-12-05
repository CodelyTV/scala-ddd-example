package tv.codely.scala_http_api.infrastructure.stubs

import scala.util.Random

object StringStub {
  def random(numChars: Int): String = Random.alphanumeric take numChars mkString ""
}
