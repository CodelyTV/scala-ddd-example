package tv.codely.scala_http_api.module.video

import tv.codely.scala_http_api.module.UnitTestCase
import tv.codely.scala_http_api.module.video.domain.{Video, VideoRepository}

protected[video] trait VideoUnitTestCase extends UnitTestCase {
  protected val repository: VideoRepository = mock[VideoRepository]

  protected def repositoryShouldSave(video: Video): Unit =
    (repository.save _)
      .expects(video)
      .returning(())

  protected def repositoryShouldSearchAll(videos: Seq[Video]): Unit =
    (repository.all _)
      .expects()
      .returning(videos)
}
