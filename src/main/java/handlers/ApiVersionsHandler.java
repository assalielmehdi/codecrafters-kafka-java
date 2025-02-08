package handlers;

import message.request.KafkaRequestMessage;
import message.response.KafkaApiVersionsResponseBody;
import message.response.KafkaResponseHeader;
import message.response.KafkaResponseMessage;
import types.Int16;
import types.Int32;

import java.util.List;

public final class ApiVersionsHandler implements RequestHandler {
  @Override
  public KafkaResponseMessage handle(KafkaRequestMessage message) {
    var apiVersion = message.header().requestApiVersion().value();
    var correlationId = message.header().correlationId().value();

    if (apiVersion != 4) {
      return new KafkaResponseMessage(
        new Int32(4 + 4 + 2),
        new KafkaResponseHeader(new Int32(correlationId)),
        new KafkaApiVersionsResponseBody(new Int16(35), List.of())
      );
    }

    var body = buildBody();

    return new KafkaResponseMessage(
      new Int32(4 + 4 + (2 + (2 + 2 + 2) * body.apiKeys().size())),
      new KafkaResponseHeader(new Int32(correlationId)),
      body
    );
  }

  private KafkaApiVersionsResponseBody buildBody() {
    return new KafkaApiVersionsResponseBody(
      new Int16(0),
      List.of(new KafkaApiVersionsResponseBody.ApiKey(
        new Int16(18), new Int16(0), new Int16(4)
      ))
    );
  }
}
