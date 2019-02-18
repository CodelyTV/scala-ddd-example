package tv.codely.shared.infrastructure.rabbitmq

import org.scalamock.scalatest.MockFactory
import spray.json.JsValue
import tv.codely.shared.domain.bus.{Message, MessagePublisher}
import tv.codely.shared.infrastructure.marshaller.MessageJsonFormatMarshaller
import tv.codely.shared.infrastructure.unit.UnitTestCase

trait MessagePublisherMock extends MockFactory {
  this: UnitTestCase => // Make mandatory to also extend UnitTestCase in order to avoid using mocks in any other kind of test.

  protected val messagePublisher: MessagePublisher = mock[MessagePublisher]

  protected def publisherShouldPublish(message: Message)(implicit writes: MessageJsonFormatMarshaller): Unit =
    (messagePublisher.publish _)
      .expects(message)
      .returning(())
}

object TestDomainEventsMarshaller {
  final class TestDomainEventsMarshaller extends MessageJsonFormatMarshaller {
    def write(m: Message): JsValue = ???

    def read(jv: JsValue): Message = ???
  }
}
