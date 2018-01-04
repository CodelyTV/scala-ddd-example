package tv.codely.scala_http_api.entry_point

import doobie.implicits._
import akka.http.scaladsl.model.{ContentTypes, StatusCodes}
import spray.json._
import tv.codely.scala_http_api.module.video.domain.VideoStub
import tv.codely.scala_http_api.module.video.infrastructure.marshaller.VideoJsValueMarshaller

final class VideoEntryPointShould extends AcceptanceSpec {
  private def cleanVideosTable() =
    sql"TRUNCATE TABLE videos".update.run
      .transact(doobieDbConnection.transactor)
      .unsafeToFuture()
      .futureValue

  "save a video" in posting(
    "/videos",
    """
      |{
      |  "id": "c5e3774b-20bb-47d3-b9fa-9f9a6e75e280",
      |  "title": "Creando proyectos #Scala con SBT new ƛ🌈",
      |  "duration_in_seconds": 698,
      |  "category": "Screencast"
      |}
    """.stripMargin
  ) {
    status shouldBe StatusCodes.NoContent
  }

  "return all the videos" in {
    cleanVideosTable()

    val videos = VideoStub.randomSeq

    videos.foreach(v => videoDependencies.repository.save(v).futureValue)

    getting("/videos") {
      status shouldBe StatusCodes.OK
      contentType shouldBe ContentTypes.`application/json`
      entityAs[String].parseJson shouldBe VideoJsValueMarshaller.marshall(videos)
    }
  }
}
