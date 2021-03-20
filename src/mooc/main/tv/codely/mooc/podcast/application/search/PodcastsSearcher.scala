package tv.codely.mooc.podcast.application.search

import tv.codely.mooc.podcast.domain.{Podcast, PodcastRepository}

import scala.concurrent.Future

final class PodcastsSearcher(repository: PodcastRepository) {
  def all(): Future[Seq[Podcast]] = repository.all()
}