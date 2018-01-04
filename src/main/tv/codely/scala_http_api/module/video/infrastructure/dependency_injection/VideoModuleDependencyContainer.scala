package tv.codely.scala_http_api.module.video.infrastructure.dependency_injection

import tv.codely.scala_http_api.module.shared.infrastructure.persistence.doobie.DoobieDbConnection
import tv.codely.scala_http_api.module.video.application.create.VideoCreator
import tv.codely.scala_http_api.module.video.application.search.VideosSearcher
import tv.codely.scala_http_api.module.video.domain.VideoRepository
import tv.codely.scala_http_api.module.video.infrastructure.repository.DoobieMySqlVideoRepository

import scala.concurrent.ExecutionContext

final class VideoModuleDependencyContainer(
    doobieDbConnection: DoobieDbConnection
)(implicit executionContext: ExecutionContext) {
  val repository: VideoRepository = new DoobieMySqlVideoRepository(doobieDbConnection)

  val videosSearcher: VideosSearcher = new VideosSearcher(repository)
  val videoCreator: VideoCreator     = new VideoCreator(repository)
}
