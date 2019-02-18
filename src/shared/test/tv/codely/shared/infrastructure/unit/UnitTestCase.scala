package tv.codely.shared.infrastructure.unit

import org.scalatest.{Matchers, WordSpec}
import org.scalatest.concurrent.ScalaFutures

trait UnitTestCase extends WordSpec with Matchers with ScalaFutures
