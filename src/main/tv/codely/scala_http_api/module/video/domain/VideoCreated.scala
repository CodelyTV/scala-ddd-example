package tv.codely.scala_http_api.module.video.domain

import tv.codely.scala_http_api.module.shared.bus.domain.Message
import tv.codely.scala_http_api.module.shared.user.domain.UserId

object VideoCreated {
  def apply(id: String, title: String, duration: BigDecimal, category: String, creatorId: String): VideoCreated = apply(
    VideoId(id),
    VideoTitle(title),
    VideoDuration(duration),
    VideoCategory(category),
    UserId(creatorId)
  )

  def apply(video: Video): VideoCreated = apply(video.id, video.title, video.duration, video.category, video.creatorId)
}

final case class VideoCreated(
    id: VideoId,
    title: VideoTitle,
    duration: VideoDuration,
    category: VideoCategory,
    creatorId: UserId
) extends Message {
  override val subType: String = "video_created"
}
