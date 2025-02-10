package handlers;

import message.request.KafkaRequestMessage;
import message.response.KafkaResponseMessage;

public sealed interface RequestHandler permits FetchHandler, ApiVersionsHandler, DescribeTopicPartitionsHandler {
  KafkaResponseMessage handle(KafkaRequestMessage request);
}
