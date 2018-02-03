package tv.codely.scala_http_api.module.course.domain

import tv.codely.scala_http_api.module.shared.domain.IntStub

object CourseTotalLessonsStub {
  def apply(value: Int): CourseTotalLessons = CourseTotalLessons(value)

  def random: CourseTotalLessons = CourseTotalLessons(IntStub.randomBetween(1, 100))
}
