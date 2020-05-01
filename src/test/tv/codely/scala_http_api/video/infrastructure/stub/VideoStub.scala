package tv.codely.scala_http_api.video.infrastructure.stub

import tv.codely.scala_http_api.course.infrastructure.stub.CourseIdStub
import tv.codely.scala_http_api.video.domain.Video

import scala.concurrent.duration.Duration

object VideoStub {
  def apply(
    id: String = VideoIdStub.random.value.toString,
    courseId: String = CourseIdStub.random.value.toString,
    title: String = VideoTitleStub.random.toString,
    duration: Duration = VideoDurationStub.random.value,
    category: String = VideoCategoryStub.random.toString,
  ): Video = Video(id, courseId, title, duration, category)

  def random: Video = apply()
}
