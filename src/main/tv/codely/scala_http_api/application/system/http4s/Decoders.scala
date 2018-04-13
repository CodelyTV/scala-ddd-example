package tv.codely.scala_http_api.application.http4s.controller

import io.circe._
import scala.concurrent.duration._

import cats.effect.Effect
import org.http4s._
import org.http4s.circe._

object Decoders {

  implicit val DurationFormat =
    new Encoder[Duration] with Decoder[Duration] {
      override def apply(a: Duration): Json =
        Encoder[Long].apply(a.toSeconds)
      override def apply(c: HCursor): Decoder.Result[Duration] =
        Decoder.decodeLong.map(s => s.seconds).apply(c)
    }

  implicit def tupleStStDecoder[P[_]](implicit Ef: Effect[P]): EntityDecoder[P, (String, String)] =
    jsonOf[P, (String, String)]

  implicit def tupleVideoDecoder[P[_]](
    implicit
    Ef: Effect[P]
  ): EntityDecoder[P, (String, String, Duration, String, String)] =
    jsonOf[P, (String, String, Duration, String, String)]
}
