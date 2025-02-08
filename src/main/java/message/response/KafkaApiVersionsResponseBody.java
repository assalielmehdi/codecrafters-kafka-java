package message.response;

import types.Int16;

import java.util.List;

public record KafkaApiVersionsResponseBody(Int16 errorCode, List<ApiKey> apiKeys) implements KafkaResponseBody {
  public record ApiKey(Int16 apiKey, Int16 minVersion, Int16 maxVersion) {}
}
