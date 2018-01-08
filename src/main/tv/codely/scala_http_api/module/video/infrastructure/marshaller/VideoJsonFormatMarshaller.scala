package tv.codely.scala_http_api.module.video.infrastructure.marshaller

import spray.json.{DefaultJsonProtocol, RootJsonFormat}
import tv.codely.scala_http_api.module.shared.infrastructure.marshaller.VideoAttributesJsonFormatMarshaller._
import tv.codely.scala_http_api.module.video.domain._

object VideoJsonFormatMarshaller extends DefaultJsonProtocol {
  implicit val videoFormat: RootJsonFormat[Video] = jsonFormat(
    Video.apply(_: VideoId, _: VideoTitle, _: VideoDuration, _: VideoCategory),
    "id",
    "title",
    "duration_in_seconds",
    "category"
  )
}
