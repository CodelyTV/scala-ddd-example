package tv.codely.scala_http_api.application.http4s.controller

import cats.FlatMap, cats.syntax.apply._, cats.syntax.flatMap._
import cats.effect._

import org.http4s._
import org.http4s.dsl.Http4sDsl
import org.http4s.circe._

import io.circe.generic.auto._
import io.circe.syntax._

import scala.concurrent.duration._
import tv.codely.scala_http_api.application.video.api._
import tv.codely.scala_http_api.application.user.api._
import Decoders._

case class VideoService[P[_]: Effect: FlatMap](
  videosSearcher: VideosSearcher[P],
  videoCreator: VideoCreator[P]
) extends Http4sDsl[P]{

  val service = HttpService[P]{
    case GET -> Root / "videos" =>
      videosSearcher.all().flatMap{
        videos => Ok(videos.asJson)
      }

    case req@(POST -> Root / "videos") =>
      req.as[(String, String, Duration, String, String)] >>= {
        case (id, title, duration, category, creatorId) => 
          videoCreator.create(
            VideoId(id), 
            VideoTitle(title), 
            VideoDuration(duration), 
            VideoCategory(category), 
            UserId(creatorId)) *>
          NoContent()
      }
  }
}