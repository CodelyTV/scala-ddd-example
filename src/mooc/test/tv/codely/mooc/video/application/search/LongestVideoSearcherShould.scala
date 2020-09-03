package tv.codely.mooc.video.application.search

import tv.codely.mooc.video.domain.VideoMother
import tv.codely.mooc.video.infrastructure.repository.VideoRepositoryMock
import tv.codely.shared.infrastructure.unit.UnitTestCase

final class LongestVideoSearcherShould extends UnitTestCase with VideoRepositoryMock {
  private val searcher = new LongestVideoSearcher(repository)

  "return empty when there are no videos" in {
    repositoryShouldFindLongest(Option.empty)

    searcher.longestVideo().futureValue shouldBe Option.empty
  }

  "search the longest video when there is a video" in {
    val video = VideoMother.random

    repositoryShouldFindLongest(Option(video))

    searcher.longestVideo().futureValue shouldBe Option(video)
  }
}
