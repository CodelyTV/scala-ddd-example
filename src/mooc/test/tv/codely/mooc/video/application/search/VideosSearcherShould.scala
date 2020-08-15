package tv.codely.mooc.video.application.search

import tv.codely.mooc.user.domain.UserIdMother
import tv.codely.mooc.video.domain._
import tv.codely.mooc.video.infrastructure.repository.VideoRepositoryMock
import tv.codely.shared.infrastructure.unit.UnitTestCase

final class VideosSearcherShould extends UnitTestCase with VideoRepositoryMock {
  private val searcher = new VideosSearcher(repository)
//  private val publisher = mock[MessagePublisher]
//  private val creator = new VideoCreator(repository, publisher)

  "search all existing videos" in {
    val existingVideo        = VideoMother.random
    val anotherExistingVideo = VideoMother.random
    val existingVideos       = Seq(existingVideo, anotherExistingVideo)

    repositoryShouldFind(existingVideos)

    searcher.all().futureValue shouldBe existingVideos
  }

  "search an existing video" in {
    val videoId = VideoIdMother.random
    val videoTitle = VideoTitleMother.random
    val videoDuration = VideoDurationMother.random
    val videoCategory = VideoCategoryMother.random
    val userId = UserIdMother.random
    val existingVideo = Video(videoId, videoTitle, videoDuration, videoCategory, userId)

    repositoryShouldSave(existingVideo)
    repositoryShouldFind(Seq(existingVideo))

    repository.save(existingVideo)
    searcher.all().futureValue.find(v => v.id.equals(existingVideo.id)).get shouldBe existingVideo

  }
}
