package tv.codely.scala_http_api.module

import org.scalamock.scalatest.MockFactory
import org.scalatest.{Matchers, WordSpec}

protected[module] trait UnitTestCase extends WordSpec with Matchers with MockFactory
