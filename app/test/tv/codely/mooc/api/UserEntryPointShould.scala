package tv.codely.mooc.api

import akka.http.scaladsl.model._
import doobie.implicits._
import org.scalatest.BeforeAndAfterEach
import spray.json._
import tv.codely.mooc.user.domain.UserMother
import tv.codely.mooc.user.infrastructure.marshaller.UserJsValueMarshaller
import tv.codely.HttpSpec

final class UserEntryPointShould extends HttpSpec with BeforeAndAfterEach {
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
    val users = UserMother.randomSeq

    users.foreach(u => userDependencies.repository.save(u).futureValue)

    getting("/users") {
      status shouldBe StatusCodes.OK
      contentType shouldBe ContentTypes.`application/json`
      entityAs[String].parseJson shouldBe UserJsValueMarshaller.marshall(users)
    }
  }
}
