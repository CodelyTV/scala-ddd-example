package tv.codely.scala_http_api.module.video.domain

import tv.codely.scala_http_api.module.shared.domain.IntStub

object SeqStub {
  val maximumElements = 10

  def randomOf[T](apply: => T): Seq[T] = (0 to IntStub.randomBetween(1, maximumElements)).map(_ => apply)
}
