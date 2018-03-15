package tv.codely.scala_http_api.effects.repositories.mock

import org.scalamock.scalatest.MockFactory
import tv.codely.scala_http_api.application.mock.UnitTestCase
import tv.codely.scala_http_api.effects.repositories.api.VideoRepository
import tv.codely.scala_http_api.application.video.api.Video

import scala.concurrent.Future

trait VideoRepositoryMock extends MockFactory {
  this: UnitTestCase => // Make mandatory to also extend UnitTestCase in order to avoid using mocks in any other kind of test.

  protected val repository: VideoRepository[Future] = mock[VideoRepository[Future]]

  protected def repositoryShouldSave(video: Video): Unit =
    (repository.save _)
      .expects(video)
      .returning(Future.unit)

  protected def repositoryShouldFind(videos: Seq[Video]): Unit =
    (repository.all _)
      .expects()
      .returning(Future.successful(videos))
}
