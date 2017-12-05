package tv.codely.scala_http_api.infrastructure

import java.util.UUID

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import spray.json.{DefaultJsonProtocol, DeserializationException, JsString, JsValue, JsonFormat, RootJsonFormat}
import tv.codely.scala_http_api.domain.{User, UserId, UserName}

object UserMarshaller extends SprayJsonSupport with DefaultJsonProtocol {
  implicit object UuidMarshaller extends JsonFormat[UUID] {
    def write(value: UUID): JsValue = JsString(value.toString)

    def read(value: JsValue): UUID = value match {
      case JsString(uuid) => UUID.fromString(uuid)
      case _              => throw DeserializationException("Expected hexadecimal UUID string")
    }
  }

  implicit val userIdFormat: RootJsonFormat[UserId]     = jsonFormat1(UserId.apply(_: UUID))
  implicit val userNameFormat: RootJsonFormat[UserName] = jsonFormat1(UserName.apply)
  implicit val userFormat: RootJsonFormat[User]         = jsonFormat2(User.apply(_: UserId, _: UserName))
}
