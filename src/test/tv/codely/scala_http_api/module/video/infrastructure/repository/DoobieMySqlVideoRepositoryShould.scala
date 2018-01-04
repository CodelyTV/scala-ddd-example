package tv.codely.scala_http_api.module.video.infrastructure.repository

import tv.codely.scala_http_api.module.video.VideoIntegrationTestCase
import tv.codely.scala_http_api.module.video.domain.VideoStub
import doobie.implicits._

final class DoobieMySqlVideoRepositoryShould extends VideoIntegrationTestCase {
  private def cleanVideosTable() =
    sql"TRUNCATE TABLE videos".update.run
      .transact(doobieDbConnection.transactor)
      .unsafeToFuture()
      .futureValue

  "return an empty sequence if there're no videos" in {
    cleanVideosTable()

    repository.all().futureValue shouldBe Seq.empty
  }

  "search all existing videos" in {
    cleanVideosTable()

    val videos = VideoStub.randomSeq

    videos.foreach(v => repository.save(v).futureValue)

    repository.all().futureValue shouldBe videos
  }
}
