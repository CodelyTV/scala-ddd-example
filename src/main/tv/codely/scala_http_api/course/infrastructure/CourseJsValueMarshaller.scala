package tv.codely.scala_http_api.course.infrastructure

import java.util.UUID

import spray.json.DefaultJsonProtocol._
import spray.json.{DeserializationException, JsString, JsValue, JsonFormat, RootJsonFormat}
import tv.codely.scala_http_api.course.domain.{Course, CourseId, CourseTitle}

object CourseJsValueMarshaller {

  implicit object CourseTitleMarshaller extends JsonFormat[CourseTitle] {
    def write(value: CourseTitle): JsValue = JsString(value.value)

    def read(value: JsValue): CourseTitle = value match {
      case JsString(title) => CourseTitle(title)
      case _               => throw DeserializationException("Expected 1 string for CourseTitle")
    }
  }

  implicit object UuidMarshaller extends JsonFormat[UUID] {
    def write(value: UUID): JsValue = JsString(value.toString)

    def read(value: JsValue): UUID = value match {
      case JsString(uuid) => UUID.fromString(uuid)
      case _              => throw DeserializationException("Expected hexadecimal UUID string")
    }
  }

  implicit object CourseIdMarshaller extends JsonFormat[CourseId] {
    def write(value: CourseId): JsValue = JsString(value.value.toString)

    def read(value: JsValue): CourseId = value match {
      case JsString(id) => CourseId(id)
      case _            => throw DeserializationException("Expected 1 string for CourseId")
    }
  }

  implicit val courseFormat: RootJsonFormat[Course] = jsonFormat2(Course.apply(_: CourseId, _: CourseTitle))
}
