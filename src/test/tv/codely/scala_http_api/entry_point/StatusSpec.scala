package tv.codely.scala_http_api.entry_point

import akka.http.scaladsl.model._

final class StatusSpec extends AcceptanceSpec {
  "ScalaHttpApi" should {
    "respond successfully while requesting its status" in get("/status") {
      status shouldBe StatusCodes.OK
      contentType shouldBe ContentTypes.`application/json`
      entityAs[String] shouldBe """{"status":"ok"}"""
    }
  }
}
