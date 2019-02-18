package tv.codely.scala_http_api.module.video.application.search

import tv.codely.scala_http_api.module.video.domain.{Video, VideoRepository}

import scala.concurrent.Future

final class VideosSearcher(repository: VideoRepository) {
  def all(): Future[Seq[Video]] = repository.all()
}
