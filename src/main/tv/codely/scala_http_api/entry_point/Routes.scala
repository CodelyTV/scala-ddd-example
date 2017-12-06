package tv.codely.scala_http_api.entry_point

import akka.http.scaladsl.server.Route
import akka.http.scaladsl.server.Directives._

final class Routes(container: EntryPointDependencyContainer) {
  val all: Route = get {
    path("status")(container.statusGetController.get()) ~
    path("users")(container.userGetController.get()) ~
    path("videos")(container.videoGetController.get())
  }
}
