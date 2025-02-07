package kafka;

import bytes.BytesWriterVisitor;

import java.io.IOException;
import java.io.OutputStream;

public class KafkaWriter {
  private final OutputStream outputStream;
  private final BytesWriterVisitor bytesWriterVisitor;

  public KafkaWriter(OutputStream outputStream, BytesWriterVisitor bytesWriterVisitor) {
    this.outputStream = outputStream;
    this.bytesWriterVisitor = bytesWriterVisitor;
  }

  public void writeHeader(KafkaHeader header) {
    try {
      outputStream.write(bytesWriterVisitor.visitInt16(header.requestApiKey()));
      outputStream.write(bytesWriterVisitor.visitInt16(header.requestApiVersion()));
      outputStream.write(bytesWriterVisitor.visitInt32(header.correlationId()));
      outputStream.write(bytesWriterVisitor.visitNullableString(header.clientId()));
    } catch (IOException ignored) {}
  }
}
