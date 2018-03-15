package tv.codely.scala_http_api.effects.repositories.doobie

import doobie.Transactor
import doobie.ConnectionIO
import doobie.syntax.connectionio._
import doobie.util.transactor.Transactor.Aux
import cats.effect.Async

final case class DoobieDbConnection[P[_]: Async](dbConfig: JdbcConfig) {
  
  val transactor: Aux[P, Unit] = Transactor.fromDriverManager[P](
    dbConfig.driver,
    dbConfig.url,
    dbConfig.user,
    dbConfig.password
  )

  def read[T](query: ConnectionIO[T]): P[T] = 
    query.transact(transactor)
}