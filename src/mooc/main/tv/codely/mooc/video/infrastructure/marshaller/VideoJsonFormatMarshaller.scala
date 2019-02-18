package tv.codely.mooc.video.infrastructure.marshaller

import spray.json.{DefaultJsonProtocol, RootJsonFormat}
import tv.codely.mooc.shared.domain.user.UserId
import tv.codely.mooc.shared.infrastructure.marshaller.user.UserIdJsonFormatMarshaller._
import tv.codely.mooc.video.domain._
import tv.codely.mooc.video.infrastructure.marshaller.VideoAttributesJsonFormatMarshaller._

object VideoJsonFormatMarshaller extends DefaultJsonProtocol {
  implicit val videoFormat: RootJsonFormat[Video] = jsonFormat(
    Video.apply(_: VideoId, _: VideoTitle, _: VideoDuration, _: VideoCategory, _: UserId),
    "id",
    "title",
    "duration_in_seconds",
    "category",
    "creator_id"
  )
}
