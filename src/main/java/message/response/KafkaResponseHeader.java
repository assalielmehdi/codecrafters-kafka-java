package message.response;

import types.Int32;

public record KafkaResponseHeader(Int32 correlationId) {
}
