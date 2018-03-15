package tv.codely.scala_http_api.application.http4s.controller

import cats.effect.Effect
import cats.implicits._

import org.http4s._
import org.http4s.dsl.Http4sDsl

import tv.codely.scala_http_api.application.api.System

class Http4sSystemService[F[_]: Effect](system: System[F]) extends Http4sDsl[F]{
  val service: HttpService[F] =
    StatusService().service <+>
    UserService(system.UsersSearcher, system.UserRegister).service <+>
    VideoService(system.VideosSearcher, system.VideoCreator).service
}

object Http4sSystemService{

  import scala.concurrent.ExecutionContext
  import fs2.Stream
  import org.http4s.server.blaze._
  import org.http4s.server.blaze.BlazeBuilder
  import fs2.StreamApp, StreamApp.ExitCode
  import com.typesafe.config.{Config, ConfigFactory}
  import tv.codely.scala_http_api.application.akkaHttp.HttpServerConfig

  class App[F[_]](
    system: Config => System[F])(implicit
    E: Effect[F],
    ec: ExecutionContext
  ) extends StreamApp[F] {

    lazy val appConfig    = ConfigFactory.load("application")
    lazy val HttpServerConfig(host, port) = HttpServerConfig(ConfigFactory.load("http-server"))

    def stream(args: List[String], requestShutdown: F[Unit]): Stream[F, ExitCode] =
      BlazeBuilder[F]
        .bindHttp(port, host)
        .mountService((new Http4sSystemService(system(appConfig))(E)).service, "/")
        .serve
  }
}
