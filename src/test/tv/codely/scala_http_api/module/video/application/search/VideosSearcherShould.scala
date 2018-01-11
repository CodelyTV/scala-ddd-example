package tv.codely.scala_http_api.module.video.application.search

import tv.codely.scala_http_api.module.video.VideoUnitTestCase
import tv.codely.scala_http_api.module.video.domain.VideoStub

final class VideosSearcherShould extends VideoUnitTestCase {
  private val searcher = new VideosSearcher(repository)

  "search all existing videos" in {
    val existingVideo        = VideoStub.random
    val anotherExistingVideo = VideoStub.random
    val existingVideos       = Seq(existingVideo, anotherExistingVideo)

    repositoryShouldFind(existingVideos)

    searcher.all().futureValue shouldBe existingVideos
  }
}
