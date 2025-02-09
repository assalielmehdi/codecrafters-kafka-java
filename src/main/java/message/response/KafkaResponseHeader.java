package message.response;

import bytes.ToBytes;
import types.Int32;

public record KafkaResponseHeader(Int32 correlationId) implements ToBytes {
  @Override
  public byte[] toBytes() {
    return ToBytes.join(
      correlationId.toBytes()
    );
  }
}
