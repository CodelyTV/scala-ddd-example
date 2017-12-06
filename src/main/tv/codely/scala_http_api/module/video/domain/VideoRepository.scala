package tv.codely.scala_http_api.module.video.domain

trait VideoRepository {
  def all(): Seq[Video]

  def save(video: Video): Unit
}
