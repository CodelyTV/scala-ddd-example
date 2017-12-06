package tv.codely.scala_http_api.entry_point

import scala.concurrent.duration._

import akka.http.scaladsl.model._
import spray.json._
import tv.codely.scala_http_api.module.user.infrastructure.marshaller.UserJsValueMarshaller
import tv.codely.scala_http_api.module.user.infrastructure.stub.UserStub
import tv.codely.scala_http_api.module.video.infrastructure.marshaller.VideoJsValueMarshaller
import tv.codely.scala_http_api.module.video.infrastructure.stub.VideoStub

final class ScalaHttpApiTest extends AcceptanceSpec {
  "ScalaHttpApi" should {
    "respond successfully while requesting its status" in {
      get("/status") {
        status shouldBe StatusCodes.OK
        contentType shouldBe ContentTypes.`application/json`
        entityAs[String] shouldBe """{"status":"ok"}"""
      }
    }

    "return all the system users" in {
      get("/users") {
        val expectedUsers = Seq(
          UserStub(id = "deacd129-d419-4552-9bfc-0723c3c4f56a", name = "Edufasio"),
          UserStub(id = "b62f767f-7160-4405-a4af-39ebb3635c17", name = "Edonisio")
        )

        status shouldBe StatusCodes.OK
        contentType shouldBe ContentTypes.`application/json`
        entityAs[String].parseJson shouldBe UserJsValueMarshaller.marshall(expectedUsers)
      }
    }

    "return all the system videos" in {
      get("/videos") {
        val expectedVideos = Seq(
          VideoStub(
            id = "3dfb19ee-260b-420a-b08c-ed58a7a07aee",
            title = "🎥 Scala FTW vol. 1",
            duration = 1.minute,
            category = "Screencast"
          ),
          VideoStub(
            id = "7341b1fc-3d80-4f6a-bcde-4fef86b01f97",
            title = "🔝 Interview with Odersky",
            duration = 30.minutes,
            category = "Interview"
          )
        )

        status shouldBe StatusCodes.OK
        contentType shouldBe ContentTypes.`application/json`
        entityAs[String].parseJson shouldBe VideoJsValueMarshaller.marshall(expectedVideos)
      }
    }
  }
}
