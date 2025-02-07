package kafka;

import bytes.BytesReader;
import types.Int32;

import java.io.InputStream;

public class KafkaParser {
  private final BytesReader bytesReader;

  public KafkaParser(InputStream inputStream) {
    this.bytesReader = new BytesReader(inputStream);
  }

  public KafkaMessage parseMessage() {
    return new KafkaMessage(
      parseLength(),
      parseHeader()
    );
  }

  private Int32 parseLength() {
    return bytesReader.readInt32();
  }

  private KafkaHeader parseHeader() {
    return new KafkaHeader(
      bytesReader.readInt16(),
      bytesReader.readInt16(),
      bytesReader.readInt32(),
      null,
      null
    );
  }
}
