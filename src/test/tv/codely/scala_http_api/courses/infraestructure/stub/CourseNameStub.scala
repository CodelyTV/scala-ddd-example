package tv.codely.scala_http_api.courses.infraestructure.stub

import tv.codely.scala_http_api.course.domain.CourseName
import tv.codely.scala_http_api.shared.infrastructure.stub.{IntStub, StringStub}

object CourseNameStub {
  private val minimumChars = 1
  private val maximumChars = 50

  def apply(value: String) : CourseName = CourseName(value)

  def random: CourseName = CourseName(
    StringStub.random(numChars = IntStub.randomBetween(minimumChars, maximumChars))
  )
}
