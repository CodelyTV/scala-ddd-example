package tv.codely.scala_http_api.module.video

import tv.codely.scala_http_api.module.UnitTestCase
import tv.codely.scala_http_api.module.video.domain.{Video, VideoRepository}

import scala.concurrent.Future

protected[video] trait VideoUnitTestCase extends UnitTestCase {
  // @ToDo: Use multiple inheritance in test suites extending from UnitTestCase and this VideoUnitTestCase
  // in order to make more explicit what we have and avoid making the UnitTestCase extending from MockFactory
  protected val repository: VideoRepository = mock[VideoRepository]

  protected def repositoryShouldSave(video: Video): Unit =
    (repository.save _)
      .expects(video)
      .returning(Future.unit)

  protected def repositoryShouldFind(videos: Seq[Video]): Unit =
    (repository.all _)
      .expects()
      .returning(Future.successful(videos))
}
