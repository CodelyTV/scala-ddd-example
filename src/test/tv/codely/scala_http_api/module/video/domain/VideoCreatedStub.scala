package tv.codely.scala_http_api.module.video.domain

import tv.codely.scala_http_api.module.shared.user.domain.UserId
import tv.codely.scala_http_api.module.user.domain.UserIdStub

object VideoCreatedStub {
  def apply(
      id: VideoId = VideoIdStub.random,
      title: VideoTitle = VideoTitleStub.random,
      duration: VideoDuration = VideoDurationStub.random,
      category: VideoCategory = VideoCategoryStub.random,
      creatorId: UserId = UserIdStub.random
  ): VideoCreated = VideoCreated(id, title, duration, category, creatorId)

  def apply(video: Video): VideoCreated = apply(video.id, video.title, video.duration, video.category, video.creatorId)

  def random: VideoCreated = apply()
}
