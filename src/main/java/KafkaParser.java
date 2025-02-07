import bytes.BytesReader;
import message.request.KafkaApiVersionsRequestBody;
import message.request.KafkaRequestHeader;
import message.request.KafkaRequestMessage;
import types.Int32;

import java.io.InputStream;

public class KafkaParser {
  private final BytesReader bytesReader;

  public KafkaParser(InputStream inputStream) {
    this.bytesReader = new BytesReader(inputStream);
  }

  public KafkaRequestMessage parseMessage() {
    var messageLength = parseLength();
    var header = parseHeader();

    return switch (header.requestApiKey().value()) {
      case 18 -> new KafkaRequestMessage(messageLength, header, parseApiVersionsBody());
      default -> new KafkaRequestMessage(messageLength, header, null);
    };
  }

  private Int32 parseLength() {
    return bytesReader.readInt32();
  }

  private KafkaRequestHeader parseHeader() {
    return new KafkaRequestHeader(
      bytesReader.readInt16(),
      bytesReader.readInt16(),
      bytesReader.readInt32(),
      null,
      null
    );
  }

  private KafkaApiVersionsRequestBody parseApiVersionsBody() {
    return new KafkaApiVersionsRequestBody();
  }
}
