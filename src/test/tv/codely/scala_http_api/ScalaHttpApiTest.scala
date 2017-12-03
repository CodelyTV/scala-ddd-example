package tv.codely.scala_http_api

import akka.http.scaladsl.model._
import akka.http.scaladsl.testkit.ScalatestRouteTest
import org.scalatest.{Matchers, WordSpec}
import org.scalatest.concurrent.ScalaFutures

final class ScalaHttpApiTest extends WordSpec with Matchers with ScalaFutures with ScalatestRouteTest {
  "ScalaHttpApi" should {

    "respond successfully while requesting its status" in {
      Get("/status") ~> Routes.all ~> check {
        status shouldBe StatusCodes.OK
        contentType shouldBe ContentTypes.`application/json`
        entityAs[String] shouldBe """{"status":"ok"}"""
      }
    }
  }
}
