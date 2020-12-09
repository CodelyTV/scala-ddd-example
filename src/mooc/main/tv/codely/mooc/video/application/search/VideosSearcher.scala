package tv.codely.mooc.video.application.search

import tv.codely.mooc.video.domain.{Video, VideoId,VideoRepository}

import scala.concurrent.Future

final class VideosSearcher(repository: VideoRepository) {
  def all(): Future[Seq[Video]] = repository.all()

  def find(videoId: VideoId): Future[Option[Video]] = {
    repository.find(videoId)
  }
}
