package tv.codely.scala_http_api

import akka.http.scaladsl.model._
import akka.http.scaladsl.testkit.ScalatestRouteTest
import org.scalatest.concurrent.ScalaFutures
import org.scalatest.{Matchers, WordSpec}
import spray.json.{JsArray, JsObject, JsString}
import tv.codely.scala_http_api.domain.User
import spray.json._

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
        val expectedSystemUsers = Seq(
          User("123", "Edufasio"),
          User("456", "Edonisio")
        )

        val marshalledUsers =
          JsArray(
            expectedSystemUsers.map(u =>
              JsObject(
                  "id" -> JsString(u.id),
                  "name" -> JsString(u.name)
              )
            ).toVector
        )

        status shouldBe StatusCodes.OK
        contentType shouldBe ContentTypes.`application/json`
        entityAs[String].parseJson shouldBe marshalledUsers
      }
    }
  }
}
