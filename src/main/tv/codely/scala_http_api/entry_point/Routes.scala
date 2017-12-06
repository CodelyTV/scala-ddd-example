package tv.codely.scala_http_api.entry_point

import akka.http.scaladsl.server.Route
import akka.http.scaladsl.server.Directives._
import tv.codely.scala_http_api.entry_point.controller.status.StatusGetController
import tv.codely.scala_http_api.entry_point.controller.video.VideoGetController

final class Routes(container: EntryPointDependencyContainer) {
  val all: Route = get {
    path("status")(StatusGetController()) ~
    path("users")(container.userGetController.get()) ~
    path("videos")(VideoGetController())
  }
}
