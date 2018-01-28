package tv.codely.scala_http_api.module.course.infrastructure.repository

import org.scalatest.BeforeAndAfterEach
import tv.codely.scala_http_api.module.course.CourseIntegrationTestCase
import doobie.implicits._
import tv.codely.scala_http_api.module.course.domain.CourseStub

final class DoobieMySqlCourseRepositoryShould extends CourseIntegrationTestCase with BeforeAndAfterEach {
  private def cleanCoursesTable() =
    sql"TRUNCATE TABLE courses".update.run
      .transact(doobieDbConnection.transactor)
      .unsafeToFuture()
      .futureValue

  override protected def beforeEach(): Unit = {
    super.beforeEach()
    cleanCoursesTable()
  }

  "return an empty sequence if there're no courses" in {
    repository.all().futureValue shouldBe Seq.empty
  }

  "search all existing courses" in {
    val courses = CourseStub.randomSeq

    courses.foreach(c => repository.save(c).futureValue)

    repository.all().futureValue shouldBe courses
  }
}
