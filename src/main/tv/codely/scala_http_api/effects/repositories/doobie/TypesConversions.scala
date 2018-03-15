package tv.codely.scala_http_api.effects.repositories.doobie

import java.util.UUID

import doobie.Meta
import tv.codely.scala_http_api.application.video.api.VideoCategory

import scala.concurrent.duration._

object TypesConversions {
  implicit val uuidMeta: Meta[UUID]                   = Meta[String].xmap(UUID.fromString, _.toString)
  implicit val durationMeta: Meta[Duration]           = Meta[Long].xmap(_.seconds, _.toSeconds)
  implicit val videoCategoryMeta: Meta[VideoCategory] = Meta[String].xmap(VideoCategory.apply, _.toString)
}
