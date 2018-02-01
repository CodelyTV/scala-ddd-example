package tv.codely.scala_http_api.module.course.application.search

import tv.codely.scala_http_api.module.course.domain.{Course, CourseRepository}

import scala.concurrent.Future

final class CoursesSearcher(repository: CourseRepository) {
  def all(): Future[Seq[Course]] = repository.all()
}
