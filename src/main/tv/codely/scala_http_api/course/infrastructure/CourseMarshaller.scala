package tv.codely.scala_http_api.course.infrastructure

import java.util.UUID

import tv.codely.scala_http_api.course.domain.{Course, CourseId, CourseName}
import spray.json.DefaultJsonProtocol._
import spray.json.{DeserializationException, JsString, JsValue, JsonFormat, RootJsonFormat}

object CourseMarshaller {

  implicit object CourseNameMarshaller extends JsonFormat[CourseName] {
    def write(value: CourseName): JsValue = JsString(value.value)

    def read(value: JsValue): CourseName = value match {
      case JsString(name) => CourseName(name)
      case _              => throw DeserializationException("Expected 1 string for CourseName")
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

  implicit val courseFormat: RootJsonFormat[Course] = jsonFormat2(Course.apply(_: CourseId, _: CourseName))
}
