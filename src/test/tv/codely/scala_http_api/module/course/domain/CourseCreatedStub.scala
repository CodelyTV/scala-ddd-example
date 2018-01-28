package tv.codely.scala_http_api.module.course.domain

import tv.codely.scala_http_api.module.shared.user.domain.UserId
import tv.codely.scala_http_api.module.user.domain.UserIdStub

object CourseCreatedStub {
  def apply(
      id: CourseId = CourseIdStub.random,
      title: CourseTitle = CourseTitleStub.random,
      lessons: CourseLessons = CourseLessonsStub.random,
      creatorId: UserId = UserIdStub.random
  ): CourseCreated = CourseCreated(id, title, lessons, creatorId)

  def apply(course: Course): CourseCreated = apply(course.id, course.title, course.lessons, course.creatorId)

  def random: CourseCreated = apply()
}
