package handlers;

import message.request.KafkaRequestMessage;
import message.response.KafkaResponseMessage;

public sealed interface RequestHandler permits ApiVersionsHandler {
  KafkaResponseMessage handle(KafkaRequestMessage request);
}
