package tv.codely.scala_http_api.module.video.domain

import java.util.UUID

object VideoId {
  def apply(value: String): VideoId = VideoId(UUID.fromString(value))
}

case class VideoId(value: UUID)
