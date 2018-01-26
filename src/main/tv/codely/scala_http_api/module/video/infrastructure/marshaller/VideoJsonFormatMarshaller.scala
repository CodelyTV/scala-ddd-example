package tv.codely.scala_http_api.module.video.infrastructure.marshaller

import spray.json.{DefaultJsonProtocol, RootJsonFormat}
import tv.codely.scala_http_api.module.shared.user.domain.UserId
import tv.codely.scala_http_api.module.shared.user.infrastructure.marshaller.UserIdJsonFormatMarshaller._
import tv.codely.scala_http_api.module.video.domain._
import tv.codely.scala_http_api.module.video.infrastructure.marshaller.VideoAttributesJsonFormatMarshaller._

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
