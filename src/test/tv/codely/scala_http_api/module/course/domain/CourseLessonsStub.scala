package tv.codely.scala_http_api.module.course.domain

import tv.codely.scala_http_api.module.shared.domain.IntStub

object CourseLessonsStub {
  def apply(value: BigDecimal): CourseLessons = CourseLessons(value)

  def random: CourseLessons = CourseLessons(IntStub.randomBetween(1, 100))
}
