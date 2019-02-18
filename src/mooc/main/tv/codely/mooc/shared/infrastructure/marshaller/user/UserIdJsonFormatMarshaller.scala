package tv.codely.mooc.shared.infrastructure.marshaller.user

import java.util.UUID

import spray.json.{DefaultJsonProtocol, JsonFormat, JsValue, _}
import tv.codely.mooc.shared.domain.user.UserId
import tv.codely.shared.infrastructure.marshaller.UuidJsonFormatMarshaller._

object UserIdJsonFormatMarshaller extends DefaultJsonProtocol {
  implicit object UserIdMarshaller extends JsonFormat[UserId] {
    override def write(value: UserId): JsValue = value.value.toJson

    override def read(value: JsValue): UserId = UserId(value.convertTo[UUID])
  }
}
