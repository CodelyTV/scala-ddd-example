package tv.codely.scala_http_api.module.course.infrastructure.repository

import doobie.implicits._
import tv.codely.scala_http_api.module.course.domain.{Course, CourseRepository}
import tv.codely.scala_http_api.module.shared.persistence.infrastructure.doobie.TypesConversions._
import tv.codely.scala_http_api.module.shared.persistence.infrastructure.doobie.DoobieDbConnection

import scala.concurrent.{ExecutionContext, Future}

final class DoobieMySqlCourseRepository(db: DoobieDbConnection)(implicit executionContext: ExecutionContext)
    extends CourseRepository {
  override def all(): Future[Seq[Course]] =
    db.read(sql"SELECT course_id, title, lessons, creator_id FROM courses".query[Course].to[Seq])

  override def save(course: Course): Future[Unit] =
    sql"INSERT INTO courses(course_id, title, lessons, creator_id) VALUES (${course.id}, ${course.title}, ${course.totalLessons}, ${course.creatorId})".update.run
      .transact(db.transactor)
      .unsafeToFuture()
      .map(_ => ())
}
