package tv.codely.scala_http_api

import org.scalatest._
import org.scalatest.Matchers._

final class ScalaHttpApiTest extends WordSpec with GivenWhenThen {
  "ScalaHttpApi" should {
    "greet" in {
      Given("a ScalaHttpApi")

      val scalaHttpApi = new ScalaHttpApi

      When("we ask him to greet someone")

      val nameToGreet = "CodelyTV"
      val greeting = scalaHttpApi.greet(nameToGreet)

      Then("it should say hello to someone")

      greeting shouldBe "Hello " + nameToGreet
    }
  }
}
