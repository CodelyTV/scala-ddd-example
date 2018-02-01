package tv.codely.scala_http_api.module.course.application.create

import tv.codely.scala_http_api.module.UnitTestCase
import tv.codely.scala_http_api.module.course.domain.{CourseCreatedStub, CourseStub}
import tv.codely.scala_http_api.module.course.infrastructure.repository.CourseRepositoryMock
import tv.codely.scala_http_api.module.shared.infrastructure.MessagePublisherMock

final class CourseCreatorShould extends UnitTestCase with CourseRepositoryMock with MessagePublisherMock {
  private val creator = new CourseCreator(repository, messagePublisher)

  "save a course" in {
    val course        = CourseStub.random
    val courseCreated = CourseCreatedStub(course)

    repositoryShouldSave(course)

    publisherShouldPublish(courseCreated)

    creator.create(course.id, course.title, course.totalLessons, course.creatorId).shouldBe(())
  }
}
