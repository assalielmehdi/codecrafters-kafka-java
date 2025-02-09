package message.response;

import bytes.ToBytes;

public sealed interface KafkaResponseBody extends ToBytes permits KafkaApiVersionsResponseBody {
}
