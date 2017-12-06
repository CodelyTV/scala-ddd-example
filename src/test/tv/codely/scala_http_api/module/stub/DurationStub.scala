package tv.codely.scala_http_api.module.stub

import scala.concurrent.duration._

object DurationStub {
  def random: Duration = IntStub.randomUnsigned().minutes
}
