package tv.codely.scala_http_api.module.video.application

import tv.codely.scala_http_api.module.video.domain.{Video, VideoRepository}

final class VideosSearcher(repository: VideoRepository) {
  def all(): Seq[Video] = repository.all()
}
