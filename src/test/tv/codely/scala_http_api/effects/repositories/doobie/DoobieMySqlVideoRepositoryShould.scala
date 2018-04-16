package tv.codely.scala_http_api.effects.repositories.doobie

import tv.codely.scala_http_api.application.stubs.video.VideoStub
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
    repository.all().unsafeToFuture.futureValue shouldBe Seq.empty
  }

  "search all existing videos" in {
    val videos = VideoStub.randomSeq

    videos.foreach(v => repository.save(v).unsafeToFuture.futureValue)

    repository.all().unsafeToFuture.futureValue shouldBe videos
  }
}
