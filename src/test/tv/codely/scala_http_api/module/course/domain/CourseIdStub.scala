package tv.codely.scala_http_api.module.course.domain

import java.util.UUID

import tv.codely.scala_http_api.module.shared.course.domain.CourseId
import tv.codely.scala_http_api.module.shared.domain.UuidStub

object CourseIdStub {
  def apply(value: String): CourseId = CourseIdStub(UuidStub(value))

  def apply(value: UUID): CourseId = CourseId(value)

  def random: CourseId = CourseId(UuidStub.random)
}
