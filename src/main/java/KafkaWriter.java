import bytes.BytesWriterVisitor;
import message.response.KafkaApiVersionsResponseBody;
import message.response.KafkaResponseBody;
import message.response.KafkaResponseHeader;
import message.response.KafkaResponseMessage;
import types.Int32;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class KafkaWriter {
  private final OutputStream outputStream;
  private final BytesWriterVisitor bytesWriterVisitor;
  private final List<byte[]> bytes;

  public KafkaWriter(OutputStream outputStream) {
    this.outputStream = outputStream;
    this.bytesWriterVisitor = new BytesWriterVisitor();
    bytes = new ArrayList<>();
  }

  public void writeMessage(KafkaResponseMessage message) {
    try {
      bytes.clear();
      writeHeader(message.header());
      writeBody(message.body());

      bytes.addFirst(bytesWriterVisitor.visitInt32(new Int32(
        bytes.stream().mapToInt(byteArray -> byteArray.length).sum() + 4
      )));

      for (var byteArray : bytes) {
        outputStream.write(byteArray);
      }
    } catch (IOException ignored) {}
  }

  private void writeHeader(KafkaResponseHeader header) {
    bytes.add(bytesWriterVisitor.visitInt32(header.correlationId()));
    bytes.add(bytesWriterVisitor.visitTagBuffer(header.tagBuffer()));
  }

  private void writeBody(KafkaResponseBody body) {
    switch (body) {
      case KafkaApiVersionsResponseBody apiVersionsBody -> writeApiVersionsBody(apiVersionsBody);
    }
  }

  private void writeApiVersionsBody(KafkaApiVersionsResponseBody body) {
    bytes.add(bytesWriterVisitor.visitInt16(body.errorCode()));

    bytes.add(bytesWriterVisitor.visitCompactArray(body.apiKeys()));

    bytes.add(bytesWriterVisitor.visitInt32(body.throttleTimeMs()));
    bytes.add(bytesWriterVisitor.visitTagBuffer(body.tagBuffer()));
  }
}
