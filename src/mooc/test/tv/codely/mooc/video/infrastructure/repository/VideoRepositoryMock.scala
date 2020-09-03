package tv.codely.mooc.video.infrastructure.repository

import org.scalamock.scalatest.MockFactory
import tv.codely.mooc.video.domain.{Video, VideoRepository}
import scala.concurrent.Future

import tv.codely.shared.infrastructure.unit.UnitTestCase

protected[video] trait VideoRepositoryMock extends MockFactory {
  this: UnitTestCase => // Make mandatory to also extend UnitTestCase in order to avoid using mocks in any other kind of test.

  protected val repository: VideoRepository = mock[VideoRepository]

  protected def repositoryShouldSave(video: Video): Unit =
    (repository.save _)
      .expects(video)
      .returning(Future.unit)

  protected def repositoryShouldFind(videos: Seq[Video]): Unit =
    (repository.all _)
      .expects()
      .returning(Future.successful(videos))

  protected def repositoryShouldFindLongest(maybeVideo: Option[Video]): Unit =
    (repository.findLongest _)
      .expects()
      .returning(Future.successful(maybeVideo))
}
