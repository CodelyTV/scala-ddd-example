package tv.codely.mooc.video.application.search

import tv.codely.mooc.video.domain.VideoMother
import tv.codely.mooc.video.domain.VideoDuration
import tv.codely.mooc.video.infrastructure.repository.VideoRepositoryMock
import tv.codely.shared.infrastructure.unit.UnitTestCase

final class ShortestVideoSearcherShould extends UnitTestCase with VideoRepositoryMock {
  private val searcher = new ShortestVideoSearcher(repository)

  "search the shortest existing video" in {
    val fiveSecondsVideo     = VideoMother.random.copy(duration = VideoDuration(5))
    val threeSecondsVideo = VideoMother.random.copy(duration = VideoDuration(3))
    val existingVideos       = Seq(fiveSecondsVideo, threeSecondsVideo)

    repositoryShouldFindShortest(existingVideos)

    searcher.shortest().futureValue shouldBe Option(threeSecondsVideo)
  }

  "return None when search the shortest existing video and there is no video" in {
    repositoryShouldFindShortestNone()

    searcher.shortest().futureValue shouldBe None
  }
}
