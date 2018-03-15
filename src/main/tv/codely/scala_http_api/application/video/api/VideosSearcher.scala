package tv.codely.scala_http_api.application.video.api

trait VideosSearcher[P[_]]{
  def all(): P[Seq[Video]]
}
