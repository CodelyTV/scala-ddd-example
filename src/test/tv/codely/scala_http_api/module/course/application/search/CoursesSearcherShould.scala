package tv.codely.scala_http_api.module.course.application.search

import tv.codely.scala_http_api.module.UnitTestCase
import tv.codely.scala_http_api.module.course.domain.CourseStub
import tv.codely.scala_http_api.module.course.infrastructure.repository.CourseRepositoryMock

final class CoursesSearcherShould extends UnitTestCase with CourseRepositoryMock {
  private val searcher = new CoursesSearcher(repository)

  "search all existing courses" in {
    val existingCourse        = CourseStub.random
    val anotherExistingCourse = CourseStub.random
    val existingCourses       = Seq(existingCourse, anotherExistingCourse)

    repositoryShouldFind(existingCourses)

    searcher.all().futureValue shouldBe existingCourses
  }
}
