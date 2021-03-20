package tv.codely.mooc.podcast.application.rating

import tv.codely.mooc.podcast.domain._

import scala.concurrent.ExecutionContext.Implicits.global

final class PodcastRater(repository: PodcastRepository) {
  def rate(
      id: PodcastId,
      value: PodcastRating
  ): Unit = {
    repository.get(id).map(
        p => {
          val newRating = (p.rating.value + value.value) / (p.votes.votes + 1)
          val podcast   = p.copy(rating = PodcastRating(newRating), votes = PodcastVotes(p.votes.votes + 1))
          repository.update(podcast)
        }
      )
  }
}