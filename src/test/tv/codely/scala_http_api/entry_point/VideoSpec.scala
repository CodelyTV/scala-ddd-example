package tv.codely.scala_http_api.entry_point

import scala.concurrent.duration._

import akka.http.scaladsl.model.{ContentTypes, StatusCodes}
import spray.json._
import tv.codely.scala_http_api.module.video.infrastructure.marshaller.VideoJsValueMarshaller
import tv.codely.scala_http_api.module.video.infrastructure.stub.VideoStub

final class VideoSpec extends AcceptanceSpec {
  "save a video" in post(
    "/videos",
    """
      |{
      |  "id": "a11098af-d352-4cce-8372-2b48b97e6942",
      |  "title": "🎥 Entrevista a SergiGP, de troll a developer!",
      |  "duration_in_seconds": 15,
      |  "category": "Interview"
      |}
    """.stripMargin
  ) {
    status shouldBe StatusCodes.NoContent
  }

  "return all the system videos" in get("/videos") {
    val expectedVideos = Seq(
      VideoStub(
        id = "a11098af-d352-4cce-8372-2b48b97e6942",
        title = "🎥 Entrevista a SergiGP, de troll a developer!",
        duration = 15.seconds,
        category = "Interview"
      )
    )

    status shouldBe StatusCodes.OK
    contentType shouldBe ContentTypes.`application/json`
    entityAs[String].parseJson shouldBe VideoJsValueMarshaller.marshall(expectedVideos)
  }
}
