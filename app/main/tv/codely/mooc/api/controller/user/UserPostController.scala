package tv.codely.mooc.api.controller.user

import akka.http.scaladsl.model.HttpResponse
import akka.http.scaladsl.model.StatusCodes.NoContent
import akka.http.scaladsl.server.Directives.complete
import akka.http.scaladsl.server.StandardRoute
import tv.codely.mooc.shared.domain.user.UserId
import tv.codely.mooc.user.application.register.UserRegistrar
import tv.codely.mooc.user.domain.UserName

final class UserPostController(registrar: UserRegistrar) {
  def post(id: String, name: String): StandardRoute = {
    registrar.register(UserId(id), UserName(name))

    complete(HttpResponse(NoContent))
  }
}
