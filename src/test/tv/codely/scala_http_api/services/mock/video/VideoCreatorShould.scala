package tv.codely.scala_http_api
package services.mock.video

import tv.codely.scala_http_api.effects.bus.mock.MessagePublisherMock
import tv.codely.scala_http_api.application.mock.UnitTestCase
import tv.codely.scala_http_api.application.stubs.video.{VideoCreatedStub, VideoStub}
import tv.codely.scala_http_api.effects.repositories.mock.VideoRepositoryMock
import tv.codely.scala_http_api.application.repo_publisher.video.VideoCreatorRepoPublisher
import scala.concurrent.{Future, ExecutionContext}, ExecutionContext.Implicits.global
import cats.instances.future._

final class VideoCreatorRepoPublisherShould extends UnitTestCase with VideoRepositoryMock with MessagePublisherMock {
  private val creator = VideoCreatorRepoPublisher[Future]()(repository, messagePublisher, implicitly)

  "save a video" in {
    val video        = VideoStub.random
    val videoCreated = VideoCreatedStub(video)

    repositoryShouldSave(video)

    publisherShouldPublish(videoCreated)

    creator.create(video.id, video.title, video.duration, video.category, video.creatorId).map(_.shouldBe(()))
  }
}
