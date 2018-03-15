package tv.codely.scala_http_api.application.akkaHttp.controller.user

import akka.http.scaladsl.model.HttpResponse
import akka.http.scaladsl.model.StatusCodes.NoContent
import akka.http.scaladsl.server.Directives.complete
import akka.http.scaladsl.server.StandardRoute
import tv.codely.scala_http_api.application.user.api.UserId
import tv.codely.scala_http_api.application.user.api.UserName
import tv.codely.scala_http_api.application.user.api.UserRegister
import scala.concurrent.{ExecutionContext, Future}

final class UserPostController(registrar: UserRegister[Future])(implicit executionContext: ExecutionContext) {
  def post(id: String, name: String): StandardRoute =
    complete(registrar.register(UserId(id), UserName(name)).map{
      _ => HttpResponse(NoContent)
    })
}
