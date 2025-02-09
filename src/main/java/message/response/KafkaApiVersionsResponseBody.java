package message.response;

import bytes.ToBytes;
import types.CompactArray;
import types.Int16;
import types.Int32;

public record KafkaApiVersionsResponseBody(Int16 errorCode, CompactArray<ApiKey> apiKeys, Int32 throttleTimeMs) implements KafkaResponseBody {
  public record ApiKey(Int16 apiKey, Int16 minVersion, Int16 maxVersion) implements ToBytes {
    @Override
    public byte[] toBytes() {
      return ToBytes.join(
        apiKey.toBytes(),
        minVersion.toBytes(),
        maxVersion.toBytes(),
        new byte[]{0}
      );
    }
  }

  @Override
  public byte[] toBytes() {
    return ToBytes.join(
      errorCode().toBytes(),
      apiKeys().toBytes(),
      throttleTimeMs().toBytes(),
      new byte[]{0}
    );
  }
}
