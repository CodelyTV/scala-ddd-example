package tv.codely.scala_http_api.user.infrastructure.marshaller

import spray.json.{JsArray, JsObject, JsString}
import tv.codely.scala_http_api.user.domain.User

object UserMarshaller {
  def marshall(users: Seq[User]): JsArray = JsArray(
    users.map(u =>
      JsObject(
        "id" -> JsString(u.id.value.toString),
        "name" -> JsString(u.name.value)
      )
    ).toVector
  )
}
