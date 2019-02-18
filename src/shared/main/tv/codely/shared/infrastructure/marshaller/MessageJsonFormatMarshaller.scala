package tv.codely.shared.infrastructure.marshaller

import spray.json.{DefaultJsonProtocol, JsValue}
import tv.codely.shared.domain.bus.Message

trait MessageJsonFormatMarshaller extends DefaultJsonProtocol {
  def write(m: Message): JsValue
  def read(jv: JsValue): Message
}
