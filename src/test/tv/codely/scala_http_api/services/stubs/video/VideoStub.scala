package tv.codely.scala_http_api.application.stubs.video

import tv.codely.scala_http_api.application.stubs.user.UserIdStub
import tv.codely.scala_http_api.application.stubs.SeqStub
import tv.codely.scala_http_api.application.video.api._

import scala.concurrent.duration.Duration

object VideoStub {
  def apply(
      id: String = VideoIdStub.random.value.toString,
      title: String = VideoTitleStub.random.value.toString,
      duration: Duration = VideoDurationStub.random.value,
      category: String = VideoCategoryStub.random.toString,
      creatorId: String = UserIdStub.random.value.toString
  ): Video = Video(id, title, duration, category, creatorId)

  def random: Video = apply()

  def randomSeq: Seq[Video] = SeqStub.randomOf(apply())
}
