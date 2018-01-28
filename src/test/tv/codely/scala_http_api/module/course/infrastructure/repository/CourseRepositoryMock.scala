package tv.codely.scala_http_api.module.course.infrastructure.repository

import org.scalamock.scalatest.MockFactory
import tv.codely.scala_http_api.module.UnitTestCase
import tv.codely.scala_http_api.module.course.domain.{Course, CourseRepository}

import scala.concurrent.Future

protected[course] trait CourseRepositoryMock extends MockFactory {
  this: UnitTestCase => // Make mandatory to also extend UnitTestCase in order to avoid using mocks in any other kind of test.

  protected val repository: CourseRepository = mock[CourseRepository]

  protected def repositoryShouldSave(course: Course): Unit =
    (repository.save _)
      .expects(course)
      .returning(Future.unit)

  protected def repositoryShouldFind(courses: Seq[Course]): Unit =
    (repository.all _)
      .expects()
      .returning(Future.successful(courses))
}
