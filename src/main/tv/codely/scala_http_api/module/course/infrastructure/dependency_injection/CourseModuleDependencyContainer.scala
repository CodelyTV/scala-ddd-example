package tv.codely.scala_http_api.module.course.infrastructure.dependency_injection

import tv.codely.scala_http_api.module.course.application.create.CourseCreator
import tv.codely.scala_http_api.module.course.application.search.CoursesSearcher
import tv.codely.scala_http_api.module.course.domain.CourseRepository
import tv.codely.scala_http_api.module.course.infrastructure.repository.DoobieMySqlCourseRepository
import tv.codely.scala_http_api.module.shared.bus.domain.MessagePublisher
import tv.codely.scala_http_api.module.shared.persistence.infrastructure.doobie.DoobieDbConnection

import scala.concurrent.ExecutionContext

final class CourseModuleDependencyContainer(
    doobieDbConnection: DoobieDbConnection,
    messagePublisher: MessagePublisher
)(implicit executionContext: ExecutionContext) {
  val repository: CourseRepository = new DoobieMySqlCourseRepository(doobieDbConnection)

  val coursesSearcher: CoursesSearcher = new CoursesSearcher(repository)
  val courseCreator: CourseCreator     = new CourseCreator(repository, messagePublisher)
}
