package tv.codely.scala_http_api.module

import org.scalatest.concurrent.ScalaFutures
import org.scalatest.{Matchers, WordSpec}

protected[module] trait UnitTestCase extends WordSpec with Matchers with ScalaFutures
