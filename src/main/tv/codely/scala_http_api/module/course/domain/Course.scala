package tv.codely.scala_http_api.module.course.domain

import tv.codely.scala_http_api.module.shared.user.domain.UserId

object Course {
  def apply(id: String, title: String, lessons: BigDecimal, creatorId: String): Course = Course(
    CourseId(id),
    CourseTitle(title),
    CourseLessons(lessons),
    UserId(creatorId)
  )
}

case class Course(id: CourseId, title: CourseTitle, lessons: CourseLessons, creatorId: UserId)
