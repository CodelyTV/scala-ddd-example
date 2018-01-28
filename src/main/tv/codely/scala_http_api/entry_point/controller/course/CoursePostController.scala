package tv.codely.scala_http_api.entry_point.controller.course

import akka.http.scaladsl.model.HttpResponse
import akka.http.scaladsl.model.StatusCodes.NoContent
import akka.http.scaladsl.server.Directives.complete
import akka.http.scaladsl.server.StandardRoute
import tv.codely.scala_http_api.module.course.application.create.CourseCreator
import tv.codely.scala_http_api.module.course.domain.{CourseId, CourseLessons, CourseTitle}
import tv.codely.scala_http_api.module.shared.user.domain.UserId

final class CoursePostController(creator: CourseCreator) {
  def post(id: String, title: String, lessons: BigDecimal, creatorId: String): StandardRoute = {
    creator.create(CourseId(id), CourseTitle(title), CourseLessons(lessons), UserId(creatorId))

    complete(HttpResponse(NoContent))
  }
}
