package message.response;

import types.Int32;

public record KafkaResponseMessage(
  Int32 messageLength,
  KafkaResponseHeader header,
  KafkaResponseBody body
) {
}
