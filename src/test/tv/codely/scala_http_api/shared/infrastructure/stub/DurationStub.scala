package tv.codely.scala_http_api.shared.infrastructure.stub

import scala.concurrent.duration._

object DurationStub {
  def random: Duration = IntStub.randomUnsigned().minutes
}
