package bytes;

import types.*;

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

  public VarInt readVarInt() {
    try {
      var value = readVarIntRaw();

      value++;
      value = (value >> 1) * ((value & 1) == 0 ? 1 : -1);

      return new VarInt(value);
    } catch (IOException e) {
      return null;
    }
  }

  public UVarInt readUVarInt() {
    try {
      var value = readVarIntRaw();

      return new UVarInt(value);
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

  private int readVarIntRaw() throws IOException {
    var value = 0;
    var b = inputStream.read();

    while ((b & 0x80) != 0) {
      value = (value << 7) | (b & 0x7F);
      b = inputStream.read();
    }

    value = (value << 7) | b;

    return value;
  }
}
