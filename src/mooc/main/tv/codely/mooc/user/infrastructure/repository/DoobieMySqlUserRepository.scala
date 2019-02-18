package tv.codely.mooc.user.infrastructure.repository

import doobie.implicits._
import tv.codely.mooc.user.domain.{User, UserRepository}
import tv.codely.mooc.shared.infrastructure.doobie.TypesConversions._
import tv.codely.shared.infrastructure.doobie.DoobieDbConnection

import scala.concurrent.{ExecutionContext, Future}

final class DoobieMySqlUserRepository(db: DoobieDbConnection)(implicit executionContext: ExecutionContext)
    extends UserRepository {
  override def all(): Future[Seq[User]] = {
    db.read(sql"SELECT user_id, name FROM users".query[User].to[Seq])
  }

  override def save(user: User): Future[Unit] =
    sql"INSERT INTO users(user_id, name) VALUES (${user.id}, ${user.name})".update.run
      .transact(db.transactor)
      .unsafeToFuture()
      .map(_ => ())
}
