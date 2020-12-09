package tv.codely.mooc.video.domain

import scala.concurrent.Future

trait VideoRepository {
  def all(): Future[Seq[Video]]

  def save(video: Video): Future[Unit]

  def find(videoId: VideoId): Future[Option[Video]]
}
