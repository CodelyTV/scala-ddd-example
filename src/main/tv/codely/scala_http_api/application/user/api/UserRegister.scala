package tv.codely.scala_http_api.application.user.api

trait UserRegister[P[_]]{
  def register(id: UserId, name: UserName): P[Unit]
}
