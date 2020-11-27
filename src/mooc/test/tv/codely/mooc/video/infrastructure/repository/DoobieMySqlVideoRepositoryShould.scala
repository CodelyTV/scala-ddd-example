package tv.codely.mooc.video.infrastructure.repository

import tv.codely.mooc.video.VideoIntegrationTestCase
import tv.codely.mooc.video.domain.{ VideoMother, VideoDuration}
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

  "search shortest existing video" in {
    val fiveSecondsVideo     = VideoMother.random.copy(duration = VideoDuration(5))
    val threeSecondsVideo = VideoMother.random.copy(duration = VideoDuration(3))
    val existingVideos       = Seq(fiveSecondsVideo, threeSecondsVideo)

    existingVideos.foreach(v => repository.save(v).futureValue)

    repository.shortest().futureValue shouldBe Option(threeSecondsVideo)
  }

  "return an empty object when search shortest existing video and there are no videos" in {
    repository.shortest().futureValue shouldBe None
  }
}
