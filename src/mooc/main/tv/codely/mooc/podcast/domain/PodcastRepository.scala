package tv.codely.mooc.podcast.domain

import scala.concurrent.Future

trait PodcastRepository {
  def all(): Future[Seq[Podcast]]
  def get(id: PodcastId): Future[Podcast]
  def save(podcast: Podcast): Future[Unit]
  def update(podcast: Podcast): Future[Unit]
}