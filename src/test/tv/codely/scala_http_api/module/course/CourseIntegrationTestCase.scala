package tv.codely.scala_http_api.module.course

import tv.codely.scala_http_api.module.IntegrationTestCase
import tv.codely.scala_http_api.module.course.domain.CourseRepository
import tv.codely.scala_http_api.module.course.infrastructure.dependency_injection.CourseModuleDependencyContainer

protected[course] trait CourseIntegrationTestCase extends IntegrationTestCase {
  private val container = new CourseModuleDependencyContainer(doobieDbConnection, messagePublisher)

  protected val repository: CourseRepository = container.repository
}
