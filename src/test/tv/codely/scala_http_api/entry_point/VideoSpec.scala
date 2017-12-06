package tv.codely.scala_http_api.entry_point

import scala.concurrent.duration._

import akka.http.scaladsl.model._
import spray.json._
import tv.codely.scala_http_api.module.video.infrastructure.marshaller.VideoJsValueMarshaller
import tv.codely.scala_http_api.module.video.infrastructure.stub.VideoStub

final class VideoSpec extends AcceptanceSpec {
  "return all the system videos" in get("/videos") {
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
