import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class ByteUtils {
  private ByteUtils() {}

  public static int readInt(InputStream buffer) throws Exception {
    var value = 0;
    var valueBytes = buffer.readNBytes(4);

    for (var i = 0; i < 4; i++) {
      value |= (valueBytes[i] & 0xFF) << (3 - i) * 8;
    }

    return value;
  }

  public static void writeInt(ByteArrayOutputStream buffer, int value) {
    var bitsShift = (4 - 1) * 8;

    while (bitsShift >= 0) {
      buffer.write((value >> bitsShift) & 0xFF);
      bitsShift -= 8;
    }
  }

  public static void writeLong(ByteArrayOutputStream buffer, long value) {
    var bitsShift = (8 - 1) * 8;

    while (bitsShift >= 0) {
      buffer.write((int) ((value >> bitsShift) & 0xFF));
      bitsShift -= 8;
    }
  }
}
