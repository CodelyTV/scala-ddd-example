package tv.codely.mooc.video.application.search

import tv.codely.mooc.video.domain.{Video, VideoRepository}

import scala.concurrent.Future

final class LongestVideoSearcher(repository: VideoRepository) {
  def longestVideo(): Future[Option[Video]] = repository.findLongest()
}
