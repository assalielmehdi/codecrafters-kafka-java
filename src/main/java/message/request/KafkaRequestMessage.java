package message.request;

import types.Int32;

public record KafkaRequestMessage(
  Int32 messageLength,
  KafkaRequestHeader header,
  KafkaRequestBody body
) {
}
