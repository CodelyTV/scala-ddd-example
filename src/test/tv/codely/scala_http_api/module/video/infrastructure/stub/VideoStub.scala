package tv.codely.scala_http_api.module.video.infrastructure.stub

import tv.codely.scala_http_api.module.video.domain.Video

import scala.concurrent.duration.Duration

object VideoStub {
  def apply(
      id: String = VideoIdStub.random.value.toString,
      title: String = VideoTitleStub.random.toString,
      duration: Duration = VideoDurationStub.random.value,
      category: String = VideoCategoryStub.random.toString,
  ): Video = Video(id, title, duration, category)

  def random: Video = apply()
}
