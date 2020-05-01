package tv.codely.scala_http_api.course.infrastructure.marshaller

import spray.json.{JsArray, JsObject, JsString}
import tv.codely.scala_http_api.course.domain.Course

object CourseMarshaller {
  def marshall(courses: Seq[Course]): JsArray = JsArray(
    courses
      .map(
        c =>
          JsObject(
            "id" -> JsString(c.id.value.toString),
            "title" -> JsString(c.title.value),
            ))
      .toVector
    )
}
