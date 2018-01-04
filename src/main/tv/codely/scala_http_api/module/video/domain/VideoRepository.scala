package tv.codely.scala_http_api.module.video.domain

import scala.concurrent.Future

trait VideoRepository {
  def all(): Future[Seq[Video]]

  def save(video: Video): Future[Unit]
}
