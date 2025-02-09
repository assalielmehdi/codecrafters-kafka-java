package handlers;

import message.request.KafkaRequestMessage;
import message.response.KafkaApiVersionsResponseBody;
import message.response.KafkaResponseHeader;
import message.response.KafkaResponseMessage;
import types.CompactArray;
import types.Int16;
import types.Int32;
import types.TagBuffer;

public final class ApiVersionsHandler implements RequestHandler {
  @Override
  public KafkaResponseMessage handle(KafkaRequestMessage message) {
    var apiVersion = message.header().requestApiVersion().value();
    var correlationId = message.header().correlationId().value();

    if (apiVersion != 4) {
      return new KafkaResponseMessage(
        new Int32(0),
        new KafkaResponseHeader(new Int32(correlationId), TagBuffer.EMPTY),
        new KafkaApiVersionsResponseBody(
          new Int16(35),
          new CompactArray<>(new KafkaApiVersionsResponseBody.ApiKey[]{}),
          new Int32(0),
          TagBuffer.EMPTY)
      );
    }

    var body = buildBody();

    return new KafkaResponseMessage(
      new Int32(0),
      new KafkaResponseHeader(new Int32(correlationId), TagBuffer.EMPTY),
      body
    );
  }

  private KafkaApiVersionsResponseBody buildBody() {
    return new KafkaApiVersionsResponseBody(
      new Int16(0),
      new CompactArray<>(new KafkaApiVersionsResponseBody.ApiKey[]{
        new KafkaApiVersionsResponseBody.ApiKey(new Int16(18), new Int16(0), new Int16(4), TagBuffer.EMPTY)
      }),
      new Int32(0),
      TagBuffer.EMPTY
    );
  }
}
