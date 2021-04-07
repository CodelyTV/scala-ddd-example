package tv.codely.mooc.video.infrastructure.dependency_injection

import tv.codely.shared.infrastructure.doobie.DoobieDbConnection
import tv.codely.mooc.video.application.create.VideoCreator
import tv.codely.mooc.video.application.search.VideosSearcher
import tv.codely.mooc.video.domain.VideoRepository
import tv.codely.mooc.video.infrastructure.repository.DoobieMySqlVideoRepository
import tv.codely.shared.domain.logger.Logger
import scala.concurrent.ExecutionContext

import tv.codely.shared.domain.bus.MessagePublisher

final class VideoModuleDependencyContainer(
    doobieDbConnection: DoobieDbConnection,
    messagePublisher: MessagePublisher,
    logger: Logger,
)(implicit executionContext: ExecutionContext) {
  val repository: VideoRepository = new DoobieMySqlVideoRepository(doobieDbConnection)

  val videosSearcher: VideosSearcher = new VideosSearcher(repository)
  val videoCreator: VideoCreator     = new VideoCreator(repository, messagePublisher, logger)
}
