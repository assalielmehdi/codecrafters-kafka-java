package message.request;

import types.Int16;
import types.Int32;

public record KafkaRequestHeader(
  Int16 requestApiKey,
  Int16 requestApiVersion,
  Int32 correlationId
) {
  public static KafkaRequestHeader fromBytes(byte[] bytes) {
    var requestApiKeyBytes = new byte[2];
    var requestApiVersionBytes = new byte[2];
    var correlationIdBytes = new byte[4];

    System.arraycopy(bytes, 0, requestApiKeyBytes, 0, 2);
    System.arraycopy(bytes, 2, requestApiVersionBytes, 0, 2);
    System.arraycopy(bytes, 4, correlationIdBytes, 0, 4);

    return new KafkaRequestHeader(
      Int16.fromBytes(requestApiKeyBytes),
      Int16.fromBytes(requestApiVersionBytes),
      Int32.fromBytes(correlationIdBytes)
    );
  }
}
