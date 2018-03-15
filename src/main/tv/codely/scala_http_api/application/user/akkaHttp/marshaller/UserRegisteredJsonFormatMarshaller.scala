package tv.codely.scala_http_api.module.user.infrastructure.marshaller

import spray.json.{DefaultJsonProtocol, DeserializationException, JsObject, JsString, JsValue, RootJsonFormat, _}
import tv.codely.scala_http_api.module.shared.user.infrastructure.marshaller.UserIdJsonFormatMarshaller._
import tv.codely.scala_http_api.application.user.api.UserRegistered
import tv.codely.scala_http_api.module.user.infrastructure.marshaller.UserNameJsonFormatMarshaller._

object UserRegisteredJsonFormatMarshaller extends DefaultJsonProtocol {

  implicit object UserRegisteredJsonFormat extends RootJsonFormat[UserRegistered] {
    override def write(ur: UserRegistered): JsValue = JsObject(
      "type" -> JsString(ur.`type`),
      "id"   -> ur.id.toJson,
      "name" -> ur.name.toJson
    )

    override def read(value: JsValue): UserRegistered =
      value.asJsObject.getFields("id", "name") match {
        case Seq(JsString(id), JsString(name)) => UserRegistered(id, name)
        case unknown                           => throw DeserializationException(s"Error reading VideoCreated JSON <$unknown>")
      }
  }

}
