package tv.codely.scala_http_api.module.user.infrastructure.marshaller

import spray.json.{DefaultJsonProtocol, DeserializationException, JsString, JsValue, JsonFormat}
import tv.codely.scala_http_api.application.user.api.UserName

object UserNameJsonFormatMarshaller extends DefaultJsonProtocol {
  implicit object UserNameMarshaller extends JsonFormat[UserName] {
    override def write(value: UserName): JsValue = JsString(value.value)

    override def read(value: JsValue): UserName = value match {
      case JsString(name) => UserName(name)
      case _              => throw DeserializationException("Expected 1 string for UserName")
    }
  }
}
