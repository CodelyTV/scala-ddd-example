package tv.codely.scala_http_api

import akka.http.scaladsl.model._
import akka.http.scaladsl.testkit.ScalatestRouteTest
import org.scalatest.concurrent.ScalaFutures
import org.scalatest.{Matchers, WordSpec}
import spray.json._
import tv.codely.scala_http_api.course.infrastructure.marshaller.CourseMarshaller
import tv.codely.scala_http_api.course.infrastructure.stub.CourseStub
import tv.codely.scala_http_api.user.infrastructure.marshaller.UserMarshaller
import tv.codely.scala_http_api.user.infrastructure.stub.UserStub
import tv.codely.scala_http_api.video.infrastructure.marshaller.VideoMarshaller
import tv.codely.scala_http_api.video.infrastructure.stub.VideoStub

import scala.concurrent.duration._

final class ScalaHttpApiTest extends WordSpec with Matchers with ScalaFutures with ScalatestRouteTest {
  "ScalaHttpApi" should {
    "respond successfully while requesting its status" in {
      Get("/status") ~> Routes.all ~> check {
        status shouldBe StatusCodes.OK
        contentType shouldBe ContentTypes.`application/json`
        entityAs[String] shouldBe """{"status":"ok"}"""
      }
    }

    "return pong while asking a ping" in {
      Get("/ping") ~> Routes.all ~> check {
        status shouldBe StatusCodes.OK
        contentType shouldBe ContentTypes.`application/json`
        entityAs[String] shouldBe """{"data":"pong"}"""
      }
    }

    "return all the system users" in {
      Get("/users") ~> Routes.all ~> check {
        val expectedUsers = Seq(
          UserStub(id = "deacd129-d419-4552-9bfc-0723c3c4f56a", name = "Edufasio"),
          UserStub(id = "b62f767f-7160-4405-a4af-39ebb3635c17", name = "Edonisio")
        )

        status shouldBe StatusCodes.OK
        contentType shouldBe ContentTypes.`application/json`
        entityAs[String].parseJson shouldBe UserMarshaller.marshall(expectedUsers)
      }
    }

    "return all the system videos" in {
      Get("/videos") ~> Routes.all ~> check {
        val expectedVideos = Seq(
          VideoStub(
            id = "3dfb19ee-260b-420a-b08c-ed58a7a07aee",
            title = "🎥 Scala FTW vol. 1",
            duration = 1.minute,
            category = "Screencast",
            course = "0dee034b-2eb2-4f3f-b75f-90b6f785d78c"
          ),
          VideoStub(
            id = "7341b1fc-3d80-4f6a-bcde-4fef86b01f97",
            title = "🔝 Interview with Odersky",
            duration = 30.minutes,
            category = "Interview",
            course = "eabb585b-4527-49e7-9529-4aa877bec55d"
          ),
          VideoStub(
            id = "cf296b95-63c3-48a2-b3fd-44a2e3ced29c",
            title = "4. JSON Parsing",
            duration = 16.minutes,
            category = "Lesson",
            course = "eabb585b-4527-49e7-9529-4aa877bec55d"
          )
        )

        status shouldBe StatusCodes.OK
        contentType shouldBe ContentTypes.`application/json`
        entityAs[String].parseJson shouldBe VideoMarshaller.marshall(expectedVideos)
      }
    }
    "return all the system courses" in {
      Get("/courses") ~> Routes.all ~> check {
        val expectedCourses = Seq(
          CourseStub(
            id = "0dee034b-2eb2-4f3f-b75f-90b6f785d78c",
            title = "Introducción a Scala"
          ),
          CourseStub(
            id = "eabb585b-4527-49e7-9529-4aa877bec55d",
            title = "API Http con Scala Akka"
          )
        )

        status shouldBe StatusCodes.OK
        contentType shouldBe ContentTypes.`application/json`
        entityAs[String].parseJson shouldBe CourseMarshaller.marshall(expectedCourses)
      }
    }
  }
}
