package tv.codely.mooc.video.application.search

import tv.codely.mooc.video.domain.VideoMother
import tv.codely.mooc.video.infrastructure.repository.VideoRepositoryMock
import tv.codely.shared.infrastructure.unit.UnitTestCase

final class ShorterVideoSearcherShould extends UnitTestCase with VideoRepositoryMock {
  private val searcher = new ShorterVideoSearcher(repository)

  "search the shortest existing video" in {
    val fiveSecondsVideo     = VideoMother.apply(duration: 5)
    val threeSecondsVideo = VideoMother.apply(duration: 3)
    val existingVideos       = Seq(fiveSecondsVideo, threeSecondsVideo)

    repositoryShouldFind(existingVideos)

    searcher.shortest().futureValue shouldBe threeSecondsVideo
  }
}
