package tv.codely.mooc.video.infrastructure.repository

import tv.codely.mooc.video.VideoIntegrationTestCase
import tv.codely.mooc.video.domain.VideoMother
import doobie.implicits._
import org.scalatest.BeforeAndAfterEach

final class DoobieMySqlVideoRepositoryShould extends VideoIntegrationTestCase with BeforeAndAfterEach {
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

  "return an empty sequence if there're no videos" in {
    repository.all().futureValue shouldBe Seq.empty
  }

  "search all existing videos" in {
    val videos = VideoMother.randomSeq

    videos.foreach(v => repository.save(v).futureValue)

    repository.all().futureValue shouldBe videos
  }

  "find an existing video" in {
    val videos = VideoMother.randomSeq
    val firstVideo = videos.head
      videos.foreach(v => repository.save(v).futureValue)

    repository.find(firstVideo.id).futureValue.get shouldBe firstVideo
  }
}
