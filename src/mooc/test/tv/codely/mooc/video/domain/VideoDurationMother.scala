package tv.codely.mooc.video.domain

import scala.concurrent.duration.Duration

import tv.codely.shared.domain.DurationMother

object VideoDurationMother {
  def apply(value: Duration): VideoDuration = VideoDuration(value)

  def random: VideoDuration = VideoDuration(DurationMother.random)
}
