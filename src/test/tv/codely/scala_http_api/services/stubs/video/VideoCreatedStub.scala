package tv.codely.scala_http_api.application.stubs.video

import tv.codely.scala_http_api.application.user.api.UserId
import tv.codely.scala_http_api.application.stubs.user.UserIdStub
import tv.codely.scala_http_api.application.video.api._

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
