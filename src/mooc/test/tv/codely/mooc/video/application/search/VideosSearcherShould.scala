package tv.codely.mooc.video.application.search

import tv.codely.mooc.video.domain.VideoMother
import tv.codely.mooc.video.infrastructure.repository.VideoRepositoryMock
import tv.codely.shared.infrastructure.unit.UnitTestCase

final class VideosSearcherShould extends UnitTestCase with VideoRepositoryMock {
  private val searcher = new VideosSearcher(repository)

  "search all existing videos" in {
    val existingVideo        = VideoMother.random
    val anotherExistingVideo = VideoMother.random
    val existingVideos       = Seq(existingVideo, anotherExistingVideo)

    repositoryShouldFind(existingVideos)

    searcher.all().futureValue shouldBe existingVideos
  }

  "search all videos that contain a term in title" in {
    val matchingVideo        = VideoMother.random
    val anotherMatchingVideo = VideoMother.random
    val matchingVideos       = Seq(matchingVideo, anotherMatchingVideo)
    val term                 = "Term"
    repositoryShouldFindByTermInTitle(term, matchingVideos)

    searcher.findByTermInTitle(term).futureValue shouldBe matchingVideos
  }
}
