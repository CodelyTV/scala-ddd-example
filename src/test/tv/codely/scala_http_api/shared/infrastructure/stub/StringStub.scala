package tv.codely.scala_http_api.shared.infrastructure.stub

import scala.util.Random

object StringStub {
  def random(numChars: Int): String = Random.alphanumeric take numChars mkString ""
}
