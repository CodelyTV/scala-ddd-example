package tv.codely.mooc.podcast.domain

import scala.concurrent.duration.{Duration, DurationLong}

object PodcastDuration {
  def apply(seconds: BigDecimal): PodcastDuration = PodcastDuration(seconds.longValue().seconds)
}

case class PodcastDuration(value: Duration)