package kafka;

import types.CompactArray;
import types.Int16;
import types.Int32;
import types.NullableString;

public record KafkaHeader(
  Int16 requestApiKey,
  Int16 requestApiVersion,
  Int32 correlationId,
  NullableString clientId,
  CompactArray tagBuffer
) {
}
