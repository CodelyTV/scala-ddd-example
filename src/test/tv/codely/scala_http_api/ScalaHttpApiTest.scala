package tv.codely.scala_http_api

import akka.http.scaladsl.model._
import akka.http.scaladsl.testkit.ScalatestRouteTest
import org.scalatest.concurrent.ScalaFutures
import org.scalatest.{ Matchers, WordSpec }
import akka.http.scaladsl.server.Directives._

final class ScalaHttpApiTest extends WordSpec with Matchers with ScalaFutures with ScalatestRouteTest {
  private val routes =
    get {
      path("status") {
        complete("""{"status":"ok"}""")
      }
    }

  "ScalaHttpApi" should {
    "respond successfully while requesting its status" in {
      Get("/status") ~> routes ~> check {
        status shouldBe StatusCodes.OK
//        contentType shouldBe ContentTypes.`application/json`
        entityAs[String] shouldBe """{"status":"ok"}"""
      }
    }
  }
}
