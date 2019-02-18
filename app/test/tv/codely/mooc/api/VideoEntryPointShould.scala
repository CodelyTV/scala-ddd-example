package tv.codely.mooc.api

import akka.http.scaladsl.model.{ContentTypes, StatusCodes}
import doobie.implicits._
import org.scalatest.BeforeAndAfterEach
import spray.json._
import tv.codely.mooc.video.domain.VideoMother
import tv.codely.mooc.video.infrastructure.marshaller.VideoJsValueMarshaller
import tv.codely.AcceptanceSpec

final class VideoEntryPointShould extends AcceptanceSpec with BeforeAndAfterEach {
  private def cleanVideosTable(): Unit =
    sql"TRUNCATE TABLE videos".update.run
      .transact(doobieDbConnection.transactor)
      .unsafeToFuture()
      .map(_ => ())
      .futureValue

  override protected def beforeEach(): Unit = {
    super.beforeEach()
    cleanVideosTable()
  }

  "save a video" in posting(
    "/videos",
    """
      |{
      |  "id": "c5e3774b-20bb-47d3-b9fa-9f9a6e75e280",
      |  "title": "Creando proyectos #Scala con SBT new ƛ🌈",
      |  "duration_in_seconds": 698,
      |  "category": "Screencast",
      |  "creator_id": "3f3df8d8-ad4c-4a1a-9d32-5c14663a409e"
      |}
    """.stripMargin
  ) {
    status shouldBe StatusCodes.NoContent
  }

  "return all the videos" in {
    val videos = VideoMother.randomSeq

    videos.foreach(v => videoDependencies.repository.save(v).futureValue)

    getting("/videos") {
      status shouldBe StatusCodes.OK
      contentType shouldBe ContentTypes.`application/json`
      entityAs[String].parseJson shouldBe VideoJsValueMarshaller.marshall(videos)
    }
  }
}
