package tv.codely.scala_http_api.module.course.infrastructure.marshaller

import spray.json.{JsArray, JsNumber, JsObject, JsString}
import tv.codely.scala_http_api.module.course.domain.Course

object CourseJsValueMarshaller {
  def marshall(courses: Seq[Course]): JsArray = JsArray(
    courses
      .map(
        c =>
          JsObject(
            "id"                  -> JsString(c.id.value.toString),
            "title"               -> JsString(c.title.value),
            "lessons"             -> JsNumber(c.lessons.value),
            "creator_id"          -> JsString(c.creatorId.value.toString)
        )
      )
      .toVector
  )
}
