package tv.codely.scala_http_api.module.shared.infrastructure.marshaller

import spray.json._
import spray.json.{DefaultJsonProtocol, DeserializationException, JsString, JsValue, RootJsonFormat, SerializationException}
import tv.codely.scala_http_api.module.shared.domain.Message
import tv.codely.scala_http_api.module.video.domain.VideoCreated
import tv.codely.scala_http_api.module.video.infrastructure.marshaller.VideoCreatedJsonFormatMarshaller._

object MessageJsonFormatMarshaller extends DefaultJsonProtocol {
  implicit object MessageMarshaller extends RootJsonFormat[Message] {
    override def write(m: Message): JsValue = m match {
      case vc: VideoCreated => vc.toJson
      case unknown          => throw new SerializationException(s"Unknown message type to write <${unknown.getClass}>")
    }

    override def read(jv: JsValue): Message = jv.asJsObject.getFields("type") match {
      case Seq(JsString("codelytv_scala_api.video_created")) => jv.convertTo[VideoCreated]
      case Seq(JsString(unknown)) =>
        throw DeserializationException(s"Unknown message type to read <$unknown>")
    }
  }
}
