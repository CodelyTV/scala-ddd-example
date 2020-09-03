package tv.codely.mooc.video.infrastructure.repository

import tv.codely.mooc.video.VideoIntegrationTestCase
import tv.codely.mooc.video.domain.{VideoDuration, VideoMother}
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

  "return an empty sequence if there are no videos" in {
    repository.all().futureValue shouldBe Seq.empty
  }

  "search all existing videos" in {
    val videos = VideoMother.randomSeq

    videos.foreach(v => repository.save(v).futureValue)

    repository.all().futureValue shouldBe videos
  }

  "return an empty option if there are no videos" in {
    repository.findLongest().futureValue shouldBe Option.empty
  }

  "search longest video" in {
    val longestVideo  = VideoMother.random.copy(duration = VideoDuration(10))
    val shorterVideo  = VideoMother.random.copy(duration = VideoDuration(5))
    val shortestVideo = VideoMother.random.copy(duration = VideoDuration(1))
    val videos        = Seq(longestVideo, shorterVideo, shortestVideo)

    videos.foreach(v => repository.save(v).futureValue)

    repository.findLongest().futureValue shouldBe Option(longestVideo)
  }
}
