package tv.codely.mooc.api

import akka.http.scaladsl.model._
import tv.codely.HttpSpec

final class StatusEntryPointShould extends HttpSpec {
  "respond successfully while requesting its status" in getting("/status") {
    status shouldBe StatusCodes.OK
    contentType shouldBe ContentTypes.`application/json`
    entityAs[String] shouldBe """{"status":"ok"}"""
  }
}
