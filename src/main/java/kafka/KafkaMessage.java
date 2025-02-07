package kafka;

import types.Int32;

public record KafkaMessage(
  Int32 messageLength,
  KafkaHeader header
) {
}
