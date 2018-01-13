package tv.codely.scala_http_api.course.domain

object Course {
  def apply(id: String, title: String): Course = Course(
    id = CourseId(id),
    title = CourseTitle(title)
  )
}

case class Course(id: CourseId, title: CourseTitle)
