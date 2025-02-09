package message.response;

import bytes.ToBytes;
import types.Int32;

public record KafkaResponseMessage(
  KafkaResponseHeader header,
  KafkaResponseBody body
) implements ToBytes {
  @Override
  public byte[] toBytes() {
    var headerBytes = header.toBytes();
    var bodyBytes = body.toBytes();

    return ToBytes.join(
      new Int32(4 + headerBytes.length + bodyBytes.length).toBytes(),
      headerBytes,
      bodyBytes
    );
  }
}
