package tv.codely.mooc.video.application.search

import tv.codely.mooc.video.domain.{Video, VideoRepository}

import scala.concurrent.Future

final class ShortestVideoSearcher(repository: VideoRepository) {
  def shortest(): Future[Option[Video]] = repository.shortest()
}
