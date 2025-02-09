package message.response;

import types.Int32;
import types.TagBuffer;

public record KafkaResponseHeader(Int32 correlationId, TagBuffer tagBuffer) {
}
