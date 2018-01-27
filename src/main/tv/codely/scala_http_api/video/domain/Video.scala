package tv.codely.scala_http_api.video.domain

import tv.codely.scala_http_api.course.domain.CourseId

import scala.concurrent.duration.Duration

object Video {
  def apply(id: String, title: String, duration: Duration, category: String, course: String): Video = Video(
    VideoId(id),
    VideoTitle(title),
    VideoDuration(duration),
    VideoCategory(category),
    CourseId(course)
  )
}

case class Video(id: VideoId, title: VideoTitle, duration: VideoDuration, category: VideoCategory, course: CourseId)
