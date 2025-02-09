package message.request;

public record KafkaApiVersionsRequestBody() implements KafkaRequestBody{
  public static KafkaRequestBody fromBytes(byte[] ignored) {
    return new KafkaApiVersionsRequestBody();
  }
}
