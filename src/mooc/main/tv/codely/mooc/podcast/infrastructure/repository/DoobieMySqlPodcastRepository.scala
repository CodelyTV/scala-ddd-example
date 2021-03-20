package tv.codely.mooc.podcast.infrastructure.repository

import doobie.implicits._
import tv.codely.mooc.podcast.domain.{Podcast, PodcastId, PodcastRepository}
import tv.codely.shared.infrastructure.doobie.DoobieDbConnection
import tv.codely.mooc.shared.infrastructure.doobie.TypesConversions._

import scala.concurrent.{ExecutionContext, Future}

final class DoobieMySqlPodcastRepository(db: DoobieDbConnection)(implicit executionContext: ExecutionContext)
  extends PodcastRepository {
  override def all(): Future[Seq[Podcast]] =
    db.read(sql"SELECT podcast_id, title, duration_in_seconds, description, rating, votes FROM podcasts".query[Podcast].to[Seq])

  override def get(podcastId: PodcastId): Future[Podcast] =
    db.read(sql"SELECT podcast_id, title, duration_in_seconds, description, rating, votes FROM podcasts Where podcast_id = ${podcastId} ".query[Podcast].unique)

  override def save(podcast: Podcast): Future[Unit] =
    sql"INSERT INTO podcasts(podcast_id, title, duration_in_seconds, description, rating, votes) VALUES (${podcast.id}, ${podcast.title}, ${podcast.duration}, ${podcast.description}, ${podcast.rating}, ${podcast.votes})".update.run
      .transact(db.transactor)
      .unsafeToFuture()
      .map(_ => ())

  override def update(podcast: Podcast): Future[Unit] =
    sql"UPDATE podcasts set rating = ${podcast.rating}, votes = ${podcast.votes} where podcast_id = ${podcast.id}".update.run
      .transact(db.transactor)
      .unsafeToFuture()
      .map(_ => ())
}