package tv.codely.scala_http_api.module.shared.user.infrastructure.marshaller

import java.util.UUID

import spray.json.{DefaultJsonProtocol, JsValue, JsonFormat, _}
import tv.codely.scala_http_api.application.akkaHttp.marshaller.UuidJsonFormatMarshaller._
import tv.codely.scala_http_api.application.user.api.UserId

object UserIdJsonFormatMarshaller extends DefaultJsonProtocol {
  implicit object UserIdMarshaller extends JsonFormat[UserId] {
    override def write(value: UserId): JsValue = value.value.toJson

    override def read(value: JsValue): UserId = UserId(value.convertTo[UUID])
  }
}
