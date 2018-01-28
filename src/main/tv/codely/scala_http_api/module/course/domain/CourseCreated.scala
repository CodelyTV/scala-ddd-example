package tv.codely.scala_http_api.module.course.domain

import tv.codely.scala_http_api.module.shared.bus.domain.Message
import tv.codely.scala_http_api.module.shared.user.domain.UserId

object CourseCreated {
  def apply(id: String, title: String, lessons: BigDecimal, creatorId: String): CourseCreated = apply(
    CourseId(id),
    CourseTitle(title),
    CourseLessons(lessons),
    UserId(creatorId)
  )

  def apply(course: Course): CourseCreated = apply(course.id, course.title, course.lessons, course.creatorId)
}

final case class CourseCreated(
    id: CourseId,
    title: CourseTitle,
    lessons: CourseLessons,
    creatorId: UserId
) extends Message {
  override val subType: String = "course_created"
}
