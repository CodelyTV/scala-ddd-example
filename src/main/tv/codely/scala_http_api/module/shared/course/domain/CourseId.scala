package tv.codely.scala_http_api.module.shared.course.domain

import java.util.UUID

object CourseId {
  def apply(value: String): CourseId = CourseId(UUID.fromString(value))
}

case class CourseId(value: UUID)
