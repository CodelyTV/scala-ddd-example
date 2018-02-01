package tv.codely.scala_http_api.module.course.domain

import scala.concurrent.Future

trait CourseRepository {
  def all(): Future[Seq[Course]]

  def save(course: Course): Future[Unit]
}
