package tv.codely.scala_http_api
package recording.code

import application.user.api.User

/**
 * 1. Partimos de una instancia genérica del repositorio de usuarios para ConnectionIO,
 * y de una instancia para MessagePublisher de tipo Id.
 */
object BaseInstances{

  /**
   * Instancia de UserRepository
   */

  import scala.concurrent.{ExecutionContext, Future}
  import doobie.implicits._
  import effects.repositories.doobie.TypesConversions._
  type DoobieDbConnection = effects.repositories.doobie.DoobieDbConnection[Future]
  import cats.implicits._
  import FunctionalAPIs.UserRepository

  final case class DoobieMySqlUserRepository()(implicit
    db: DoobieDbConnection,
    ec: ExecutionContext) extends UserRepository[Future] {
    
    override def all(): Future[Seq[User]] = {
      db.read(sql"SELECT user_id, name FROM users".query[User].to[Seq])
    }

    override def save(user: User): Future[Unit] =
      sql"INSERT INTO users(user_id, name) VALUES (${user.id}, ${user.name})".update.run
        .transact(db.transactor)
        .map(_ => ())
  }


  /** 
   * Instancia de MessagePublisher
   */
  import cats.Id
  import FunctionalAPIs.MessagePublisher
  import effects.bus.rabbit_mq._

  def RabbitMqInstance(implicit 
    busConfig: RabbitMqConfig): MessagePublisher[Id] = ???
}


/**
 * 2. Inyectando dependencias: primer intento
 */
object InyentadoDependenciasI{
  import cats.instances.future._
  import effects.bus.rabbit_mq._
  import BaseInstances._
  import FunctionalLogicII._
  import scala.concurrent.{Future, ExecutionContext}

  // Recordatorio de la clase a instanciar:

  // final case class UserRegisterRepoPublisher[P[_]](
  //   repository: UserRepository[P], 
  //   publisher: MessagePublisher[P])(implicit 
  //   F: Apply[P])
  // extends UserRegister[P]{ ... }

  // def userRegisterDoobieRabbitMQFuture(implicit 
  //   doobieCon: DoobieDbConnection,
  //   busConfig: RabbitMqConfig,
  //   ec: ExecutionContext
  // ): UserRegisterRepoPublisher[Future] = ???

  // ¡No compila! :(

  // def userRegisterDoobieRabbitMQFuture(implicit 
  //   doobieCon: DoobieDbConnection,
  //   busConfig: RabbitMqConfig,
  //   ec: ExecutionContext
  // ): UserRegisterRepoPublisher[Future] = 
  //   UserRegisterRepoPublisher[Future](
  //     DoobieMySqlUserRepository(),
  //     RabbitMqInstance)
}

/**
 * 3. Hacen falta adaptadores para las instancia de MessagePublisher. 
 * Primera aproximación ad-hoc.
 */
object DerivedInstancesI{
  import cats.Id
  import scala.concurrent.{Future, ExecutionContext}
  import effects.bus.api.Message
  import FunctionalAPIs.MessagePublisher

  implicit def messagePublisherTrans(mp: MessagePublisher[Id])(implicit
    ec: ExecutionContext): MessagePublisher[Future] = 
    new MessagePublisher[Future]{
      def publish(message: Message): Future[Unit] = 
        Future(mp.publish(message))
    }
}

/**
 * 4. Inyectando dependencias: intento exitoso, aunque demasiado específico.
 */
object InyentadoDependenciasII{
  import cats.instances.future._
  import effects.bus.rabbit_mq.RabbitMqConfig
  import FunctionalLogicII.UserRegisterRepoPublisher
  import BaseInstances._
  import scala.concurrent.{Future, ExecutionContext}

  import DerivedInstancesI._

  def userRegisterDoobieRabbitMQFuture(implicit 
    doobieCon: DoobieDbConnection,
    busConfig: RabbitMqConfig,
    ec: ExecutionContext
  ): UserRegisterRepoPublisher[Future] = 
    UserRegisterRepoPublisher[Future](
      DoobieMySqlUserRepository(),
      RabbitMqInstance)
}

/**
 * 5. Derivemos nuestras instancias de forma más genérica.
 */
object DerivedInstancesII{
  import cats.Id
  import scala.concurrent.{Future, ExecutionContext}
  import effects.bus.api.Message
  import FunctionalAPIs.MessagePublisher

  // implicit def messagePublisherTrans(mp: MessagePublisher[Id])(implicit
  //   ec: ExecutionContext): MessagePublisher[Future] = 
  //   new MessagePublisher[Future]{
  //     def publish(message: Message): Future[Unit] = 
  //       Future(mp.publish(message))
  //   }

  import cats.effect.IO

  // implicit def messagePublisherTransIO(mp: MessagePublisher[Id])(implicit
  //   ec: ExecutionContext): MessagePublisher[IO] = 
  //   new MessagePublisher[IO]{
  //     def publish(message: Message): IO[Unit] = 
  //       IO(mp.publish(message))
  //   }

  import cats.~>
  implicit def messagePublisherTrans[P[_],Q[_]](
      mp: MessagePublisher[P])(implicit Q: P~>Q): MessagePublisher[Q] = 
    new MessagePublisher[Q]{
      def publish(message: Message): Q[Unit] = 
        Q(mp.publish(message))
    }

  implicit def fromIdToFuture(implicit ec: ExecutionContext): Id ~> Future = new (Id ~> Future){
    def apply[T](a: Id[T]): Future[T] = 
      Future(a)
  }
}

