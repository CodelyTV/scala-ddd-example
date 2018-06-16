package tv.codely.scala_http_api.courses.infraestructure.marshaller

import spray.json.{JsArray, JsObject, JsString}
import tv.codely.scala_http_api.course.domain.Course

object CourseMarshaller {
  def marshall(courses: Seq[Course]): JsArray = JsArray(
    courses.map(
      course =>
        JsObject(
          "id"   -> JsString(course.id.value.toString),
          "name" -> JsString(course.name.value.toString)
        ))
      .toVector
  )
}
