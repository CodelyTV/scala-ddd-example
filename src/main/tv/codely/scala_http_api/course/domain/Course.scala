package tv.codely.scala_http_api.course.domain

object Course {
  def apply(id: String, title: String): Course = Course(
    CourseId(id),
    CourseTitle(title)
    )
}

case class Course(id: CourseId, title: CourseTitle)
