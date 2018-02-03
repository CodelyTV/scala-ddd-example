package tv.codely.scala_http_api.module.course.domain

import tv.codely.scala_http_api.module.shared.bus.domain.Message
import tv.codely.scala_http_api.module.shared.course.domain.CourseId
import tv.codely.scala_http_api.module.shared.user.domain.UserId

object CourseCreated {
  def apply(id: String, title: String, totalLessons: Int, creatorId: String): CourseCreated = apply(
    CourseId(id),
    CourseTitle(title),
    CourseTotalLessons(totalLessons),
    UserId(creatorId)
  )

  def apply(course: Course): CourseCreated = apply(course.id, course.title, course.totalLessons, course.creatorId)
}

final case class CourseCreated(
    id: CourseId,
    title: CourseTitle,
    totalLessons: CourseTotalLessons,
    creatorId: UserId
) extends Message {
  override val subType: String = "course_created"
}
