package tv.codely.scala_http_api.effects.bus.api

trait MessagePublisher[P[_]]{
  def publish(message: Message): P[Unit]
}

object MessagePublisher{

  import cats.~>

  implicit def toQ[P[_], Q[_]](implicit 
    P: MessagePublisher[P],
    nat: P ~> Q) = new MessagePublisher[Q]{
      def publish(message: Message): Q[Unit] =
        nat(P.publish(message))
  }

  implicit def toQView[P[_], Q[_]](P: MessagePublisher[P])(implicit 
    nat: P ~> Q): MessagePublisher[Q] = toQ(P, nat)

}
