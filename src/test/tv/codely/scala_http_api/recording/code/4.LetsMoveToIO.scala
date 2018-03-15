package tv.codely.scala_http_api
package recording.code

import application.user.api.User

/**
 * 1. ¡Pero todavía seguimos generando instancias para Future!
 * ¿Cómo podemos hacer las instancias más genéricas?
 */
object BaseInstancesII{

  import FunctionalAPIs.UserRepository

  /**
   * Instancia de UserRepository
   */

  // import scala.concurrent.{ExecutionContext, Future}
  // import doobie.implicits._
  // import effects.repositories.doobie.TypesConversions._
  // import effects.repositories.doobie.DoobieDbConnection
  // import cats.implicits._

  // final case class DoobieMySqlUserRepository()(implicit
  //   db: DoobieDbConnection[Future],
  //   ec: ExecutionContext) extends UserRepository[Future] {
    
  //   override def all(): Future[Seq[User]] = {
  //     sql"SELECT user_id, name FROM users".query[User].to[Seq]
  //       .transact(db.transactor)
  //   }

  //   override def save(user: User): Future[Unit] =
  //     sql"INSERT INTO users(user_id, name) VALUES (${user.id}, ${user.name})".update.run
  //       .transact(db.transactor)
  //       .map(_ => ())
  // }

  import scala.concurrent.{ExecutionContext, Future}
  import doobie.implicits._
  import effects.repositories.doobie.DoobieDbConnection
  import effects.repositories.doobie.TypesConversions._
  import cats.effect.Async, cats.Monad, cats.syntax.functor._

  final case class DoobieMySqlUserRepository[P[_]: Async]()(implicit
    db: DoobieDbConnection[P]) extends UserRepository[P] {
    
    override def all(): P[Seq[User]] = {
      sql"SELECT user_id, name FROM users".query[User].to[Seq]
        .transact(db.transactor)
    }

    override def save(user: User): P[Unit] =
      sql"INSERT INTO users(user_id, name) VALUES (${user.id}, ${user.name})".update.run
        .transact(db.transactor)
        .map(_ => ())
  }  
}


/**
 * 2. Ahora tenemos que adaptar las instancias de Id a cats.effect.IO
 */
object DerivedInstancesIII{
  import cats.Id, cats.effect.IO, cats.~>
  
  // implicit def fromIdToFuture(implicit ec: ExecutionContext): Id ~> Future = new (Id ~> Future){
  //   def apply[T](a: Id[T]): Future[T] = 
  //     Future(a)
  // }
  
  implicit val fromIdToIO: Id ~> IO = new (Id~>IO){
    def apply[T](a: Id[T]): IO[T] = 
      IO(a)
  }

}

/**
 * 3. Inyectando dependencias para cats.IO
 */
object InyentadoDependenciasIII{
  import cats.effect.IO
  import cats.instances.future._
  import effects.bus.rabbit_mq.RabbitMqConfig
  import effects.repositories.doobie.DoobieDbConnection
  import FunctionalLogicII.UserRegisterRepoPublisher
  import BaseInstances.RabbitMqInstance
  import BaseInstancesII.DoobieMySqlUserRepository
  import scala.concurrent.{Future, ExecutionContext}

  import DerivedInstancesII._, DerivedInstancesIII._

  // def userRegisterDoobieRabbitMQFuture(implicit 
  //   doobieCon: DoobieDbConnection,
  //   busConfig: RabbitMqConfig,
  //   ec: ExecutionContext
  // ): UserRegisterRepoPublisher[Future] = 
  //   UserRegisterRepoPublisher[Future](
  //     DoobieMySqlUserRepository(),
  //     RabbitMqInstance)

  // def userRegisterDoobieRabbitMQFuture(implicit 
  //   doobieCon: DoobieDbConnection[Future],
  //   busConfig: RabbitMqConfig,
  //   ec: ExecutionContext
  // ): UserRegisterRepoPublisher[Future] = 
  //   UserRegisterRepoPublisher[Future](
  //     DoobieMySqlUserRepository[Future](),
  //     RabbitMqInstance)

  def userRegisterDoobieRabbitMQIO(implicit 
    doobieCon: DoobieDbConnection[IO],
    busConfig: RabbitMqConfig
  ): UserRegisterRepoPublisher[IO] = 
    UserRegisterRepoPublisher[IO](
      DoobieMySqlUserRepository[IO](),
      RabbitMqInstance)
}
