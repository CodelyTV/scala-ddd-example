package tv.codely.scala_http_api.module.course.infrastructure.marshaller

import java.util.UUID

import spray.json.{DeserializationException, JsNumber, JsString, JsValue, JsonFormat, _}
import tv.codely.scala_http_api.module.course.domain.{CourseLessons, CourseTitle}
import tv.codely.scala_http_api.module.shared.course.domain.CourseId
import tv.codely.scala_http_api.module.shared.marshaller.infrastructure.UuidJsonFormatMarshaller._

object CourseAttributesJsonFormatMarshaller {
  implicit object CourseIdMarshaller extends JsonFormat[CourseId] {
    override def write(value: CourseId): JsValue = value.value.toJson

    override def read(value: JsValue): CourseId = CourseId(value.convertTo[UUID])
  }

  implicit object CourseTitleMarshaller extends JsonFormat[CourseTitle] {
    override def write(value: CourseTitle): JsValue = JsString(value.value)

    override def read(value: JsValue): CourseTitle = value match {
      case JsString(name) => CourseTitle(name)
      case _              => throw DeserializationException("Expected 1 string for CourseTitle")
    }
  }

  implicit object CourseLessonsMarshaller extends JsonFormat[CourseLessons] {
    override def write(value: CourseLessons): JsValue = JsNumber.apply(value.value)

    override def read(value: JsValue): CourseLessons = value match {
      case JsNumber(totalLessons) => CourseLessons(totalLessons.toInt)
      case _                      => throw DeserializationException("Expected 1 string for CourseLessons")
    }
  }

}
