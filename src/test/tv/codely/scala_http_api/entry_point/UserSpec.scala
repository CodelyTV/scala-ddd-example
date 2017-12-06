package tv.codely.scala_http_api.entry_point

import akka.http.scaladsl.model._
import spray.json._
import tv.codely.scala_http_api.module.user.infrastructure.marshaller.UserJsValueMarshaller
import tv.codely.scala_http_api.module.user.infrastructure.stub.UserStub

final class UserSpec extends AcceptanceSpec {
  "return all the system users" in get("/users") {
    val expectedUsers = Seq(
      UserStub(id = "deacd129-d419-4552-9bfc-0723c3c4f56a", name = "Edufasio"),
      UserStub(id = "b62f767f-7160-4405-a4af-39ebb3635c17", name = "Edonisio")
    )

    status shouldBe StatusCodes.OK
    contentType shouldBe ContentTypes.`application/json`
    entityAs[String].parseJson shouldBe UserJsValueMarshaller.marshall(expectedUsers)
  }
}
