package tv.codely.scala_http_api.courses.infraestructure.stub

import java.util.UUID

import tv.codely.scala_http_api.course.domain.CourseId
import tv.codely.scala_http_api.shared.infrastructure.stub.UuidStub

object CourseIdStub {

  def apply(value: String): CourseId = CourseIdStub(UuidStub(value))

  def apply(value: UUID): CourseId = CourseId(value)

  def random: CourseId = CourseId(UuidStub.random)
}
