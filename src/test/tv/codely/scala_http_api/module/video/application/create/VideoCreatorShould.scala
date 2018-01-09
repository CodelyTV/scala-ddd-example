package tv.codely.scala_http_api.module.video.application.create

import tv.codely.scala_http_api.module.UnitTestCase
import tv.codely.scala_http_api.module.shared.infrastructure.MessagePublisherMock
import tv.codely.scala_http_api.module.video.domain.{VideoCreatedStub, VideoStub}
import tv.codely.scala_http_api.module.video.infrastructure.repository.VideoRepositoryMock

final class VideoCreatorShould extends UnitTestCase with VideoRepositoryMock with MessagePublisherMock {
  private val creator = new VideoCreator(repository, messagePublisher)

  "save a video" in {
    val video        = VideoStub.random
    val videoCreated = VideoCreatedStub(video)

    repositoryShouldSave(video)

    publisherShouldPublish(videoCreated)

    creator.create(video.id, video.title, video.duration, video.category).shouldBe(())
  }
}
