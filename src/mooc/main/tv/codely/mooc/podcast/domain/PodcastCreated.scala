package tv.codely.mooc.podcast.domain

import tv.codely.shared.domain.bus.Message

object PodcastCreated {
  def apply(id: String, title: String, duration: BigDecimal, description: String, rating: BigDecimal, votes: Int): PodcastCreated = apply(
    PodcastId(id),
    PodcastTitle(title),
    PodcastDuration(duration),
    PodcastDescription(description),
    PodcastRating(rating),
    PodcastVotes(votes)
  )

  def apply(podcast: Podcast): PodcastCreated =
    apply(podcast.id, podcast.title, podcast.duration, podcast.description, podcast.rating, podcast.votes)
}

final case class PodcastCreated(
    id: PodcastId,
    title: PodcastTitle,
    duration: PodcastDuration,
    description: PodcastDescription,
    rating: PodcastRating,
    votes: PodcastVotes
) extends Message {
  override val subType: String = "podcast_created"
}