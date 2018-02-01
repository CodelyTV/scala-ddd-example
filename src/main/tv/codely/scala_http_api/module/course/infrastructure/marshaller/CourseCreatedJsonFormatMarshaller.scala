package tv.codely.scala_http_api.module.course.infrastructure.marshaller

import spray.json.{DefaultJsonProtocol, DeserializationException, JsString, JsValue, RootJsonFormat, _}
import tv.codely.scala_http_api.module.course.domain.CourseCreated
import tv.codely.scala_http_api.module.course.infrastructure.marshaller.CourseAttributesJsonFormatMarshaller._
import tv.codely.scala_http_api.module.shared.user.infrastructure.marshaller.UserIdJsonFormatMarshaller._

object CourseCreatedJsonFormatMarshaller extends DefaultJsonProtocol {

  implicit object CourseCreatedJsonFormat extends RootJsonFormat[CourseCreated] {
    override def write(c: CourseCreated): JsValue = JsObject(
      "type"          -> JsString(c.`type`),
      "id"            -> c.id.toJson,
      "title"         -> c.title.toJson,
      "total_lessons" -> c.totalLessons.toJson,
      "creator_id"    -> c.creatorId.toJson
    )

    override def read(value: JsValue): CourseCreated =
      value.asJsObject.getFields("id", "title", "total_lessons", "creator_id") match {
        case Seq(JsString(id), JsString(title), JsNumber(totalLessons), JsString(creatorId)) =>
          CourseCreated(id, title, totalLessons.toInt, creatorId)
        case unknown =>
          throw DeserializationException(
            s"Error reading CourseCreated JSON <$unknown>"
          )
      }
  }

}
