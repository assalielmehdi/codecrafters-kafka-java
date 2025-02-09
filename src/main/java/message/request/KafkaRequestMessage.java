package message.request;

import types.Int32;

public record KafkaRequestMessage(
  Int32 messageLength,
  KafkaRequestHeader header,
  KafkaRequestBody body
) {
  private static final int API_VERSIONS_REQUEST_API_KEY = 18;

  public static KafkaRequestMessage fromBytes(byte[] bytes) {
    var messageLengthBytes = new byte[4];
    var headerBytes = new byte[12];
    var bodyBytes = new byte[bytes.length - 16];

    System.arraycopy(bytes, 0, messageLengthBytes, 0, 4);
    System.arraycopy(bytes, 4, headerBytes, 0, 12);
    System.arraycopy(bytes, 16, bodyBytes, 0, bytes.length - 16);

    var header = KafkaRequestHeader.fromBytes(headerBytes);

    return new KafkaRequestMessage(
      Int32.fromBytes(messageLengthBytes),
      header,
      switch (header.requestApiKey().value()) {
        case API_VERSIONS_REQUEST_API_KEY -> KafkaApiVersionsRequestBody.fromBytes(bodyBytes);
        default -> null;
      }
    );
  }
}
