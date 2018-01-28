package tv.codely.scala_http_api.entry_point

import doobie.implicits._
import akka.http.scaladsl.model.{ContentTypes, StatusCodes}
import org.scalatest.BeforeAndAfterEach
import spray.json._
import tv.codely.scala_http_api.module.course.domain.CourseStub
import tv.codely.scala_http_api.module.course.infrastructure.marshaller.CourseJsValueMarshaller

final class CourseEntryPointShould extends AcceptanceSpec with BeforeAndAfterEach {
  private def cleanCoursesTable() =
    sql"TRUNCATE TABLE courses".update.run
      .transact(doobieDbConnection.transactor)
      .unsafeToFuture()
      .futureValue

  override protected def beforeEach(): Unit = {
    super.beforeEach()
    cleanCoursesTable()
  }

  "save a course" in posting(
    "/courses",
    """
      |{
      |  "id": "c5e3774b-20bb-47d3-b9fa-9f9a6e75e280",
      |  "title": "Mi primera API HTTP con #Scala y Akka ƛ🌈",
      |  "lessons": 12,
      |  "creator_id": "3f3df8d8-ad4c-4a1a-9d32-5c14663a409e"
      |}
    """.stripMargin
  ) {
    status shouldBe StatusCodes.NoContent
  }

  "return all the courses" in {
    val courses = CourseStub.randomSeq

    courses.foreach(c => courseDependencies.repository.save(c).futureValue)

    getting("/courses") {
      status shouldBe StatusCodes.OK
      contentType shouldBe ContentTypes.`application/json`
      entityAs[String].parseJson shouldBe CourseJsValueMarshaller.marshall(courses)
    }
  }
}
