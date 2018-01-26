package tv.codely.scala_http_api.module.shared.infrastructure

import org.scalamock.scalatest.MockFactory
import tv.codely.scala_http_api.module.UnitTestCase
import tv.codely.scala_http_api.module.shared.bus.domain.{Message, MessagePublisher}

protected[module] trait MessagePublisherMock extends MockFactory {
  this: UnitTestCase => // Make mandatory to also extend UnitTestCase in order to avoid using mocks in any other kind of test.

  protected val messagePublisher: MessagePublisher = mock[MessagePublisher]

  protected def publisherShouldPublish(message: Message): Unit =
    (messagePublisher.publish _)
      .expects(message)
      .returning(())
}
