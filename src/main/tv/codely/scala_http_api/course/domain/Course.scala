package tv.codely.scala_http_api.course.domain

object Course {
  def apply(id: String, name: String): Course = Course(CourseId(id), CourseName(name))
}

case class Course(id: CourseId, name: CourseName)
