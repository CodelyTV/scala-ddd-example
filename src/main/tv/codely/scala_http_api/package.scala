package tv.codely

package object scala_http_api {

  import cats.{~>, Id}
  import scala.concurrent.Future
  import cats.effect.IO

  implicit def fromIdToFuture: Id ~> Future =
    λ[Id ~> Future](Future.successful(_))

  implicit def fromIOToFuture: IO ~> Future =
    λ[IO ~> Future](io => io.unsafeToFuture)

  implicit def fromIdToIO: Id ~> IO =
    λ[Id ~> IO](IO.apply(_))
}
