package tv.codely.scala_http_api.module.shared.persistence.infrastructure.doobie

import cats.effect.IO
import doobie.Transactor
import doobie.syntax.ConnectionIOOps
import doobie.util.transactor.Transactor.Aux

import scala.concurrent.Future

final class DoobieDbConnection(dbConfig: JdbcConfig) {
  val transactor: Aux[IO, Unit] = Transactor.fromDriverManager[IO](
    dbConfig.driver,
    dbConfig.url,
    dbConfig.user,
    dbConfig.password
  )

  def read[T](query: ConnectionIOOps[T]): Future[T] = query.transact(transactor).unsafeToFuture()
}
