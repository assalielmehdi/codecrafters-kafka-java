package bytes;

import types.Int16;
import types.Int32;
import types.NullableString;

import java.io.IOException;
import java.io.InputStream;

public class BytesReader {
  private final InputStream inputStream;

  public BytesReader(InputStream inputStream) {
    this.inputStream = inputStream;
  }

  public Int16 readInt16() {
    try {
      return new Int16(
        (inputStream.read() << 8)
          | inputStream.read()
      );
    } catch (IOException e) {
      return null;
    }
  }

  public Int32 readInt32() {
    try {
      return new Int32(
        (inputStream.read() << 24)
          | (inputStream.read() << 16)
          | (inputStream.read() << 8)
          | inputStream.read()
      );
    } catch (IOException e) {
      return null;
    }
  }

  public NullableString readNullableString() {
    try {
      var length = readInt16();

      if (length.value() == -1) {
        return new NullableString(null);
      }

      var value = new StringBuilder();

      for (var i = 0; i < length.value(); i++) {
        value.append((char) inputStream.read());
      }

      return new NullableString(value.toString());
    } catch (IOException e) {
      return null;
    }
  }
}
