package tv.codely.scala_http_api.module.shared.infrastructure

import org.scalamock.scalatest.MockFactory
import tv.codely.scala_http_api.module.shared.domain.{Message, MessagePublisher}

protected[module] trait MessagePublisherMock extends MockFactory {
  protected val messagePublisher: MessagePublisher = mock[MessagePublisher]

  protected def publisherShouldPublish(message: Message): Unit =
    (messagePublisher.publish _)
      .expects(message)
      .returning(())
}
