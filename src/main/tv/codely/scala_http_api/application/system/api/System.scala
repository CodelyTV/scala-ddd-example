package tv.codely.scala_http_api.application.api

import tv.codely.scala_http_api.application.video.api.VideoCreator
import tv.codely.scala_http_api.application.video.api.VideosSearcher
import tv.codely.scala_http_api.application.user.api.UserRegister
import tv.codely.scala_http_api.application.user.api.UsersSearcher

trait System[P[_]]{
  val UserRegister: UserRegister[P]
  val UsersSearcher: UsersSearcher[P]
  val VideoCreator: VideoCreator[P]
  val VideosSearcher: VideosSearcher[P]
}
