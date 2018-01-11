package tv.codely.scala_http_api.module.video.infrastructure.marshaller

import spray.json.{DefaultJsonProtocol, DeserializationException, JsString, JsValue, RootJsonFormat}
import tv.codely.scala_http_api.module.video.domain._
import VideoAttributesJsonFormatMarshaller._
import spray.json._

object VideoCreatedJsonFormatMarshaller extends DefaultJsonProtocol {
  implicit object VideoCreatedJsonFormat extends RootJsonFormat[VideoCreated] {
    override def write(c: VideoCreated): JsValue = JsObject(
      "type"     -> JsString(c.`type`),
      "id"       -> c.id.toJson,
      "title"    -> c.title.toJson,
      "duration" -> c.duration.toJson,
      "category" -> c.category.toJson
    )

    override def read(value: JsValue): VideoCreated =
      value.asJsObject.getFields("id", "title", "duration", "category") match {
        case Seq(JsString(id), JsString(title), JsNumber(duration), JsString(category)) =>
          VideoCreated(id, title, duration, category)
        case unknown => throw DeserializationException(s"Error reading VideoCreated JSON <$unknown>")
      }
  }
}
