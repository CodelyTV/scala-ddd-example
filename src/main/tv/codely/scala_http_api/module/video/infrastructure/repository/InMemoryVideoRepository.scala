package tv.codely.scala_http_api.module.video.infrastructure.repository

import tv.codely.scala_http_api.module.video.domain.{Video, VideoRepository}

final class InMemoryVideoRepository extends VideoRepository {
  private var videos: Seq[Video] = Seq[Video]()

  def all(): Seq[Video] = videos

  def save(video: Video): Unit = videos = videos :+ video
}
