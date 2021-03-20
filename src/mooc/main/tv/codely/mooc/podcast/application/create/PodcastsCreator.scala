package tv.codely.mooc.podcast.application.create

import tv.codely.mooc.podcast.domain._
import tv.codely.mooc.shared.infrastructure.marshaller.DomainEventsMarshaller.MessageMarshaller
import tv.codely.shared.domain.bus.MessagePublisher

final class PodcastsCreator(repository: PodcastRepository, publisher: MessagePublisher) {
  def create(
      id: PodcastId,
      title: PodcastTitle,
      duration: PodcastDuration,
      description: PodcastDescription,
  ): Unit = {
    val podcast = Podcast(id, title, duration, description, PodcastRating(0), PodcastVotes(0))

    repository.save(podcast)

    publisher.publish(PodcastCreated(podcast))(MessageMarshaller)
  }
}