package tv.codely.mooc.podcast.domain

import java.util.UUID

object PodcastId {
  def apply(value: String): PodcastId = PodcastId(UUID.fromString(value))
}

case class PodcastId(value: UUID)