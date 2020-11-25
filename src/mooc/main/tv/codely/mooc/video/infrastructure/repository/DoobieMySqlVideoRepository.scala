package tv.codely.mooc.video.infrastructure.repository

import doobie.implicits._
import tv.codely.mooc.video.domain.{Video, VideoRepository}
import tv.codely.mooc.shared.infrastructure.doobie.TypesConversions._
import tv.codely.shared.infrastructure.doobie.DoobieDbConnection

import scala.concurrent.{ExecutionContext, Future}

final class DoobieMySqlVideoRepository(db: DoobieDbConnection)(implicit executionContext: ExecutionContext)
    extends VideoRepository {
  override def all(): Future[Seq[Video]] =
    db.read(sql"SELECT video_id, title, duration_in_seconds, category, creator_id FROM videos".query[Video].to[Seq])

  override def save(video: Video): Future[Unit] =
    sql"INSERT INTO videos(video_id, title, duration_in_seconds, category, creator_id) VALUES (${video.id}, ${video.title}, ${video.duration}, ${video.category}, ${video.creatorId})".update.run
      .transact(db.transactor)
      .unsafeToFuture()
      .map(_ => ())

  override def shortest(): Future[Optional[Video]] =
    db.read(sql"SELECT video_id, title, duration_in_seconds, category, creator_id FROM videos ORDER BY duration_in_seconds ASC").query[Video].take(1).option
}
