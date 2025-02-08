import bytes.BytesWriterVisitor;
import message.response.KafkaApiVersionsResponseBody;
import message.response.KafkaResponseBody;
import message.response.KafkaResponseHeader;
import message.response.KafkaResponseMessage;

import java.io.IOException;
import java.io.OutputStream;

public class KafkaWriter {
  private final OutputStream outputStream;
  private final BytesWriterVisitor bytesWriterVisitor;

  public KafkaWriter(OutputStream outputStream) {
    this.outputStream = outputStream;
    this.bytesWriterVisitor = new BytesWriterVisitor();
  }

  public void writeMessage(KafkaResponseMessage message) {
    try {
      outputStream.write(bytesWriterVisitor.visitInt32(message.messageLength()));
      writeHeader(message.header());
      writeBody(message.body());
    } catch (IOException ignored) {}
  }

  private void writeHeader(KafkaResponseHeader header) {
    try {
      outputStream.write(bytesWriterVisitor.visitInt32(header.correlationId()));
    } catch (IOException ignored) {}
  }

  private void writeBody(KafkaResponseBody body) {
    switch (body) {
      case KafkaApiVersionsResponseBody apiVersionsBody -> writeApiVersionsBody(apiVersionsBody);
    }
  }

  private void writeApiVersionsBody(KafkaApiVersionsResponseBody body) {
    try {
      outputStream.write(bytesWriterVisitor.visitInt16(body.errorCode()));

      for (var apiKey : body.apiKeys()) {
        outputStream.write(bytesWriterVisitor.visitInt16(apiKey.apiKey()));
        outputStream.write(bytesWriterVisitor.visitInt16(apiKey.minVersion()));
        outputStream.write(bytesWriterVisitor.visitInt16(apiKey.maxVersion()));
      }
    } catch (IOException ignored) {}
  }
}
