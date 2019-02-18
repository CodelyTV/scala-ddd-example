package tv.codely.mooc.video.domain

import tv.codely.shared.domain.{IntMother, StringMother}

object VideoTitleMother {
  private val minimumChars = 1
  private val maximumChars = 50

  def apply(value: String): VideoTitle = VideoTitle(value)

  def random: VideoTitle = VideoTitle(
    StringMother.random(numChars = IntMother.randomBetween(minimumChars, maximumChars))
  )
}
