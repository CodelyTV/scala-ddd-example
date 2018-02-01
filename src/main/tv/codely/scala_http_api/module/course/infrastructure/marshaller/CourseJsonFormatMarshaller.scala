package tv.codely.scala_http_api.module.course.infrastructure.marshaller

import spray.json.{DefaultJsonProtocol, RootJsonFormat}
import tv.codely.scala_http_api.module.course.domain.{Course, CourseLessons, CourseTitle}
import tv.codely.scala_http_api.module.shared.user.domain.UserId
import tv.codely.scala_http_api.module.shared.user.infrastructure.marshaller.UserIdJsonFormatMarshaller._
import tv.codely.scala_http_api.module.course.infrastructure.marshaller.CourseAttributesJsonFormatMarshaller._
import tv.codely.scala_http_api.module.shared.course.domain.CourseId

object CourseJsonFormatMarshaller extends DefaultJsonProtocol {
  implicit val courseFormat: RootJsonFormat[Course] = jsonFormat(
    Course.apply(_: CourseId, _: CourseTitle, _: CourseLessons, _: UserId),
    "id",
    "title",
    "lessons",
    "creator_id"
  )
}
