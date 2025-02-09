package message.request;

import types.*;

public record KafkaRequestHeader(
  Int16 requestApiKey,
  Int16 requestApiVersion,
  Int32 correlationId,
  NullableString clientId,
  TagBuffer tagBuffer
) {
}
