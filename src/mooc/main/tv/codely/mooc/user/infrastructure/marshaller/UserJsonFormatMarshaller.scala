package tv.codely.mooc.user.infrastructure.marshaller

import spray.json.{DefaultJsonProtocol, RootJsonFormat}
import tv.codely.mooc.shared.domain.user.UserId
import tv.codely.mooc.shared.infrastructure.marshaller.user.UserIdJsonFormatMarshaller._
import tv.codely.mooc.user.domain.{User, UserName}
import tv.codely.mooc.user.infrastructure.marshaller.UserNameJsonFormatMarshaller._

object UserJsonFormatMarshaller extends DefaultJsonProtocol {
  implicit val userFormat: RootJsonFormat[User] = jsonFormat2(User.apply(_: UserId, _: UserName))
}
