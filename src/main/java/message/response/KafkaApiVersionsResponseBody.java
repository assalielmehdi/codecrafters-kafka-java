package message.response;

import types.Int16;

public record KafkaApiVersionsResponseBody(Int16 errorCode) implements KafkaResponseBody {
}
