package tv.codely.mooc.video.domain

import java.util.UUID

object VideoId {
  def apply(value: String): VideoId = VideoId(UUID.fromString(value))
}

case class VideoId(value: UUID)
