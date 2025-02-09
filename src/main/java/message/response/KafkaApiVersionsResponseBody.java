package message.response;

import types.CompactArray;
import types.Int16;
import types.Int32;
import types.TagBuffer;

public record KafkaApiVersionsResponseBody(Int16 errorCode, CompactArray<ApiKey> apiKeys, Int32 throttleTimeMs, TagBuffer tagBuffer) implements KafkaResponseBody {
  public record ApiKey(Int16 apiKey, Int16 minVersion, Int16 maxVersion, TagBuffer tagBuffer) {}
}
