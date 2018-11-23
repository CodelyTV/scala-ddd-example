package tv.codely.scala_http_api.module.video.infrastructure.marshaller

import spray.json.{DefaultJsonProtocol, DeserializationException, JsString, JsValue, RootJsonFormat, _}
import tv.codely.scala_http_api.module.shared.user.infrastructure.marshaller.UserIdJsonFormatMarshaller._
import tv.codely.scala_http_api.module.video.domain._
import tv.codely.scala_http_api.module.video.infrastructure.marshaller.VideoAttributesJsonFormatMarshaller._

object VideoCreatedJsonFormatMarshaller extends DefaultJsonProtocol {

  implicit object VideoCreatedJsonFormat extends RootJsonFormat[VideoCreated] {
    override def write(event: VideoCreated): JsValue = JsObject(
      "type" -> JsString(event.`type`),
      "id"   -> event.id.toJson,
      "attributes" -> JsObject(
        "id"                  -> event.id.toJson,
        "title"               -> event.title.toJson,
        "duration_in_seconds" -> event.duration.toJson,
        "category"            -> event.category.toJson,
        "creator_id"          -> event.creatorId.toJson
      )
    )

    override def read(value: JsValue): VideoCreated =
      value.asJsObject.getFields("id", "title", "duration_in_seconds", "category", "creator_id") match {
        case Seq(JsString(id), JsString(title), JsNumber(duration), JsString(category), JsString(creatorId)) =>
          VideoCreated(id, title, duration, category, creatorId)
        case unknown =>
          throw DeserializationException(
            s"Error reading VideoCreated JSON <$unknown>"
          )
      }
  }

}
