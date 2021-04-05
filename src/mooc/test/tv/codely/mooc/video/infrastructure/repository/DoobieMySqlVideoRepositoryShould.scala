package tv.codely.mooc.video.infrastructure.repository

import tv.codely.mooc.video.VideoIntegrationTestCase
import tv.codely.mooc.video.domain.VideoMother
import tv.codely.mooc.video.domain.VideoTitleMother
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

  "search all videos that contain a term in title" in {
    val term         = "Term"
    val videoMatch   = VideoMother.random.copy(title = VideoTitleMother("This has a Term inside"))
    val videoNoMatch = VideoMother.random.copy(title = VideoTitleMother("This has not a ? inside"))

    repository.save(videoMatch).futureValue
    repository.save(videoNoMatch).futureValue

    repository.findByTermInTitle(term).futureValue should contain(videoMatch)
    repository.findByTermInTitle(term).futureValue should not contain (videoNoMatch)
  }
}
