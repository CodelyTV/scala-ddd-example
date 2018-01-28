package tv.codely.scala_http_api.module.course.domain

import tv.codely.scala_http_api.module.shared.domain.{IntStub, StringStub}

object CourseTitleStub {
  private val minimumChars = 1
  private val maximumChars = 50

  def apply(value: String): CourseTitle = CourseTitle(value)

  def random: CourseTitle = CourseTitle(
    StringStub.random(numChars = IntStub.randomBetween(minimumChars, maximumChars))
  )
}
