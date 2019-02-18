package tv.codely.mooc.video.application.create

import tv.codely.mooc.shared.infrastructure.marshaller.DomainEventsMarshaller
import tv.codely.mooc.video.domain.{VideoCreatedMother, VideoMother}
import tv.codely.mooc.video.infrastructure.repository.VideoRepositoryMock
import tv.codely.shared.infrastructure.rabbitmq.MessagePublisherMock
import tv.codely.shared.infrastructure.unit.UnitTestCase

final class VideoCreatorShould extends UnitTestCase with VideoRepositoryMock with MessagePublisherMock {
  private val creator = new VideoCreator(repository, messagePublisher)

  "save a video" in {
    val video        = VideoMother.random
    val videoCreated = VideoCreatedMother(video)

    repositoryShouldSave(video)

    publisherShouldPublish(videoCreated)(new DomainEventsMarshaller)

    creator.create(video.id, video.title, video.duration, video.category, video.creatorId).shouldBe(())
  }
}
