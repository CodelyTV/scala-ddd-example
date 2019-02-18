package tv.codely.shared.infrastructure.rabbitmq

import org.scalamock.scalatest.MockFactory
import spray.json.RootJsonFormat
import tv.codely.shared.domain.bus.{Message, MessagePublisher}
import tv.codely.shared.infrastructure.unit.UnitTestCase

trait MessagePublisherMock extends MockFactory {
  this: UnitTestCase => // Make mandatory to also extend UnitTestCase in order to avoid using mocks in any other kind of test.

  protected val messagePublisher: MessagePublisher = mock[MessagePublisher]

  protected def publisherShouldPublish(message: Message)(implicit writes: RootJsonFormat[Message]): Unit =
    (messagePublisher
      .publish(_: Message)(_: RootJsonFormat[Message]))
      .expects(message, writes)
      .returning(())
}
