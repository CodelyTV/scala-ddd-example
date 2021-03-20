package tv.codely.mooc.podcast.infrastructure.dependency_injection

import tv.codely.mooc.podcast.application.create.PodcastsCreator
import tv.codely.mooc.podcast.application.rating.PodcastRater
import tv.codely.mooc.podcast.application.search.PodcastsSearcher
import tv.codely.mooc.podcast.domain.PodcastRepository
import tv.codely.mooc.podcast.infrastructure.repository.DoobieMySqlPodcastRepository
import tv.codely.shared.domain.bus.MessagePublisher
import tv.codely.shared.infrastructure.doobie.DoobieDbConnection

import scala.concurrent.ExecutionContext

final class PodcastModuleDependencyContainer(
    doobieDbConnection: DoobieDbConnection,
    messagePublisher: MessagePublisher
)(implicit executionContext: ExecutionContext) {
  val repository: PodcastRepository = new DoobieMySqlPodcastRepository(doobieDbConnection)

  val podcastsSearcher: PodcastsSearcher = new PodcastsSearcher(repository)
  val podcastCreator: PodcastsCreator    = new PodcastsCreator(repository, messagePublisher)
  val podcastRater: PodcastRater         = new PodcastRater(repository)
}