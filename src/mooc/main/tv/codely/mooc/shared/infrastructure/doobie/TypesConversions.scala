package tv.codely.mooc.shared.infrastructure.doobie

import java.util.UUID

import scala.concurrent.duration._

import doobie.Meta
import tv.codely.mooc.video.domain.VideoCategory

object TypesConversions {
  implicit val uuidMeta: Meta[UUID]                   = Meta[String].xmap(UUID.fromString, _.toString)
  implicit val durationMeta: Meta[Duration]           = Meta[Long].xmap(_.seconds, _.toSeconds)
  implicit val videoCategoryMeta: Meta[VideoCategory] = Meta[String].xmap(VideoCategory.apply, _.toString)
}
