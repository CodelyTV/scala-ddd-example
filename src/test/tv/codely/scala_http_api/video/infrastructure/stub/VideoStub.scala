package tv.codely.scala_http_api.video.infrastructure.stub

import tv.codely.scala_http_api.course.domain.Course
import tv.codely.scala_http_api.course.infrastructure.stub.CourseStub
import tv.codely.scala_http_api.video.domain.Video

import scala.concurrent.duration.Duration

object VideoStub {
  def apply(
      id: String = VideoIdStub.random.value.toString,
      title: String = VideoTitleStub.random.toString,
      duration: Duration = VideoDurationStub.random.value,
      category: String = VideoCategoryStub.random.toString,
      course: Course = CourseStub.random
  ): Video = Video(id, title, duration, category, course)

  def random: Video = apply()
}
