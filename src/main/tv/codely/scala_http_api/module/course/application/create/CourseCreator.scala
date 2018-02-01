package tv.codely.scala_http_api.module.course.application.create

import tv.codely.scala_http_api.module.course.domain._
import tv.codely.scala_http_api.module.shared.bus.domain.MessagePublisher
import tv.codely.scala_http_api.module.shared.course.domain.CourseId
import tv.codely.scala_http_api.module.shared.user.domain.UserId

final class CourseCreator(repository: CourseRepository, publisher: MessagePublisher) {
  def create(
      id: CourseId,
      title: CourseTitle,
      lessons: CourseLessons,
      creatorId: UserId
  ): Unit = {
    val course = Course(id, title, lessons, creatorId)

    repository.save(course)

    publisher.publish(CourseCreated(course))
  }

}
