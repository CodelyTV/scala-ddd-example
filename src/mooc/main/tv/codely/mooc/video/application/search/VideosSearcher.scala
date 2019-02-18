package tv.codely.mooc.video.application.search

import tv.codely.mooc.video.domain.{Video, VideoRepository}

import scala.concurrent.Future

final class VideosSearcher(repository: VideoRepository) {
  def all(): Future[Seq[Video]] = repository.all()
}
