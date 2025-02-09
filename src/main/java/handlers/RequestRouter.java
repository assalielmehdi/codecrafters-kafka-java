package handlers;

import message.request.KafkaRequestMessage;
import message.response.KafkaResponseMessage;
import types.Int16;

import java.util.Map;

public class RequestRouter {
  private final Map<Int16, RequestHandler> handlers;

  public RequestRouter() {
    this.handlers = Map.of(
      new Int16(18), new ApiVersionsHandler()
    );
  }

  public KafkaResponseMessage route(KafkaRequestMessage message) {
    var handler = handlers.get(message.header().requestApiKey());

    if (handler == null) {
      return null;
    }

    return handler.handle(message);
  }
}
