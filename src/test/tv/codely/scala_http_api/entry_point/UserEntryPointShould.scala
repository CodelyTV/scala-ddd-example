package tv.codely.scala_http_api.entry_point

import akka.http.scaladsl.model._
import doobie.implicits._
import org.scalatest.BeforeAndAfterEach
import spray.json._
import tv.codely.scala_http_api.application.stubs.user.UserStub
import tv.codely.scala_http_api.application.akkaHttp.marshaller.UserJsValueMarshaller

final class UserEntryPointShould extends AcceptanceSpec with BeforeAndAfterEach {
  private def cleanUsersTable(): Unit =
    sql"TRUNCATE TABLE users".update.run
      .transact(doobieDbConnection.transactor)
      .unsafeToFuture()
      .map(_ => ())
      .futureValue

  override protected def beforeEach(): Unit = {
    super.beforeEach()
    cleanUsersTable()
  }

  "save a user" in posting(
    "/users",
    """
      |{
      |  "id": "a11098af-d352-4cce-8372-2b48b97e6942",
      |  "name": "Codelyver ✌️"
      |}
    """.stripMargin
  ) {
    status shouldBe StatusCodes.NoContent
  }

  "return all the users" in {
    val users = UserStub.randomSeq

    users.foreach(u => doobieUserRepo.save(u).unsafeToFuture.futureValue)

    getting("/users") {
      status shouldBe StatusCodes.OK
      contentType shouldBe ContentTypes.`application/json`
      entityAs[String].parseJson shouldBe UserJsValueMarshaller.marshall(users)
    }
  }
}
