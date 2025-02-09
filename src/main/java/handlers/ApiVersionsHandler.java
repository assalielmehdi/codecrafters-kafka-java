package handlers;

import message.request.KafkaRequestMessage;
import message.response.KafkaApiVersionsResponseBody;
import message.response.KafkaResponseHeader;
import message.response.KafkaResponseMessage;
import types.CompactArray;
import types.Int16;
import types.Int32;

public final class ApiVersionsHandler implements RequestHandler {
  @Override
  public KafkaResponseMessage handle(KafkaRequestMessage message) {
    var apiVersion = message.header().requestApiVersion().value();
    var correlationId = message.header().correlationId().value();

    var header = new KafkaResponseHeader(new Int32(correlationId));
    var body = switch (apiVersion) {
      case 4 -> new KafkaApiVersionsResponseBody(
        new Int16(0),
        new CompactArray<>(new KafkaApiVersionsResponseBody.ApiKey[]{
          new KafkaApiVersionsResponseBody.ApiKey(new Int16(18), new Int16(0), new Int16(4))
        }),
        new Int32(100)
      );
      default -> new KafkaApiVersionsResponseBody(
        new Int16(35),
        new CompactArray<>(new KafkaApiVersionsResponseBody.ApiKey[]{}),
        new Int32(0)
      );
    };

    return new KafkaResponseMessage(header, body);
  }
}
