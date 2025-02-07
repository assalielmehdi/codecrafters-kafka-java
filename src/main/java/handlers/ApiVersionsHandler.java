package handlers;

import message.request.KafkaRequestMessage;
import message.response.KafkaApiVersionsResponseBody;
import message.response.KafkaResponseHeader;
import message.response.KafkaResponseMessage;
import types.Int16;
import types.Int32;

public final class ApiVersionsHandler implements RequestHandler {
  @Override
  public KafkaResponseMessage handle(KafkaRequestMessage message) {
    var apiVersion = message.header().requestApiVersion().value();
    var correlationId = message.header().correlationId().value();

    if (apiVersion < 0 || apiVersion > 4) {
      return new KafkaResponseMessage(
        new Int32(0),
        new KafkaResponseHeader(new Int32(correlationId)),
        new KafkaApiVersionsResponseBody(new Int16(35))
      );
    }

    return new KafkaResponseMessage(
      new Int32(0),
      new KafkaResponseHeader(new Int32(correlationId)),
      new KafkaApiVersionsResponseBody(new Int16(0))
    );
  }
}
