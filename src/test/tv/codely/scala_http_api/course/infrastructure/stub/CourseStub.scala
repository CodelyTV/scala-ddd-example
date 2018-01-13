package tv.codely.scala_http_api.course.infrastructure.stub

import tv.codely.scala_http_api.course.domain.Course

object CourseStub {
  def apply(
             id: String = CourseIdStub.random.value.toString,
             title: String = CourseTitleStub.random.toString
  ): Course = Course(id, title)

  def random: Course = apply()
}
