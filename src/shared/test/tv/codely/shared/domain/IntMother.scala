package tv.codely.shared.domain

import scala.util.Random

object IntMother {
  def random: Int = Random.nextInt()

  def randomUnsigned(max: Int = Int.MaxValue - 1): Int = randomBetween(min = 0, max = max)

  def randomBetween(min: Int, max: Int): Int = Random.nextInt((max - min) + 1)
}
