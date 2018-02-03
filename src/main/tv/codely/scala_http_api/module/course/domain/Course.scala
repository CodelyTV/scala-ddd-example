package tv.codely.scala_http_api.module.course.domain

import tv.codely.scala_http_api.module.shared.course.domain.CourseId
import tv.codely.scala_http_api.module.shared.user.domain.UserId

object Course {
  def apply(id: String, title: String, totalLessons: Int, creatorId: String): Course = Course(
    CourseId(id),
    CourseTitle(title),
    CourseTotalLessons(totalLessons),
    UserId(creatorId)
  )
}

case class Course(id: CourseId, title: CourseTitle, totalLessons: CourseTotalLessons, creatorId: UserId)
