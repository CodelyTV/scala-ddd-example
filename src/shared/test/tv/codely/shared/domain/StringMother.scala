package tv.codely.shared.domain

import scala.util.Random

object StringMother {
  def random(numChars: Int): String = Random.alphanumeric take numChars mkString ""
}
