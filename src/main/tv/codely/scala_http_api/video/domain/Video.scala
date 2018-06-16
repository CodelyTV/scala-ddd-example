package tv.codely.scala_http_api.video.domain

import tv.codely.scala_http_api.course.domain.CourseId

import scala.concurrent.duration.Duration

object Video {
  def apply(id: String, courseId: String, title: String, duration: Duration, category: String): Video = Video(
    VideoId(id),
    CourseId(courseId),
    VideoTitle(title),
    VideoDuration(duration),
    VideoCategory(category)
  )
}

case class Video(id: VideoId, courseId: CourseId, title: VideoTitle, duration: VideoDuration, category: VideoCategory)
