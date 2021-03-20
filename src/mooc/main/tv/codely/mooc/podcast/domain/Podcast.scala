package tv.codely.mooc.podcast.domain

import scala.concurrent.duration.Duration

object Podcast {
  def apply(id: String, title: String, duration: Duration, description: String, rating: BigDecimal, votes: Int): Podcast = Podcast(
    PodcastId(id),
    PodcastTitle(title),
    PodcastDuration(duration),
    PodcastDescription(description),
    PodcastRating(rating),
    PodcastVotes(votes)
  )
}

case class Podcast(id: PodcastId,
                   title: PodcastTitle,
                   duration: PodcastDuration,
                   description: PodcastDescription,
                   rating: PodcastRating,
                   votes: PodcastVotes)