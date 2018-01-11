package tv.codely.scala_http_api.module.user.infrastructure.repository

import doobie.implicits._
import tv.codely.scala_http_api.module.user.domain.{User, UserRepository}
import tv.codely.scala_http_api.module.shared.infrastructure.persistence.doobie.DoobieDbConnection
import tv.codely.scala_http_api.module.shared.infrastructure.persistence.doobie.TypesConversions._

import scala.concurrent.Future

final class DoobieMySqlUserRepository(db: DoobieDbConnection) extends UserRepository {
  override def all(): Future[Seq[User]] = {
    db.read(sql"SELECT user_id, name FROM users".query[User].to[Seq])
  }
}
