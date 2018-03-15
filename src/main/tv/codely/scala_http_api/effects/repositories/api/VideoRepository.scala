package tv.codely.scala_http_api.effects.repositories.api

import tv.codely.scala_http_api.application.video.api.Video

trait VideoRepository[P[_]] {
  def all(): P[Seq[Video]]

  def save(video: Video): P[Unit]
}

object VideoRepository{
  import cats.~>

  implicit def toQ[P[_], Q[_]](implicit 
    P: VideoRepository[P],
    nat: P ~> Q) = new VideoRepository[Q]{
      def all(): Q[Seq[Video]] = nat(P.all())
      def save(video: Video): Q[Unit] = nat(P.save(video))
  }
}
