package tv.codely.scala_http_api.application.repo_publisher.video

import tv.codely.scala_http_api.application.video.api._
import tv.codely.scala_http_api.effects.repositories.api.VideoRepository

final case class VideosSearcherRepo[P[_]]()(implicit
  repository: VideoRepository[P])
extends VideosSearcher[P] {
  def all(): P[Seq[Video]] = repository.all()
}
