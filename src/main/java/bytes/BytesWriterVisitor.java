package bytes;

import types.*;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BytesWriterVisitor implements KafkaPrimitiveTypeVisitor<byte[]> {
  @Override
  public byte[] visitInt16(Int16 int16) {
    try (
      var baos = new ByteArrayOutputStream();
      var dos = new DataOutputStream(baos);
    ) {
      dos.writeInt(int16.value());
      var bytes = baos.toByteArray();

      return new byte[]{bytes[2], bytes[3]};
    } catch (IOException e) {
      return null;
    }
  }

  @Override
  public byte[] visitInt32(Int32 int32) {
    try (
      var baos = new ByteArrayOutputStream();
      var dos = new DataOutputStream(baos);
    ) {
      dos.writeInt(int32.value());

      return baos.toByteArray();
    } catch (IOException e) {
      return null;
    }
  }

  @Override
  public byte[] visitVarInt(VarInt varInt) {
    try (var baos = new ByteArrayOutputStream();) {
      var zigzagValue = varInt.value() >= 0 ? varInt.value() << 1 : (-varInt.value() << 1) - 1;
      var zigzagBytes = encodeVarInt(zigzagValue);

      baos.write(zigzagBytes);

      return baos.toByteArray();
    } catch (IOException e) {
      return null;
    }
  }

  @Override
  public byte[] visitUVarInt(UVarInt uVarInt) {
    try (var baos = new ByteArrayOutputStream();) {
      var valueBytes = encodeVarInt(uVarInt.value());

      baos.write(valueBytes);

      return baos.toByteArray();
    } catch (IOException e) {
      return null;
    }
  }

  @Override
  public byte[] visitNullableString(NullableString nullableString) {
    try (
      var baos = new ByteArrayOutputStream();
      var dos = new DataOutputStream(baos);
    ) {
      if (nullableString.value() == null) {
        return visitInt16(new Int16(-1));
      }

      var length = nullableString.value().length();
      dos.write(visitInt16(new Int16(length)));

      for (var i = 0; i < length; i++) {
        dos.writeChar(nullableString.value().charAt(i));
      }

      return baos.toByteArray();
    } catch (IOException e
    ) {
      return null;
    }
  }

  @Override
  public <V> byte[] visitCompactArray(CompactArray<V> compactArray) {
    try (
      var baos = new ByteArrayOutputStream();
    ) {
      visitUVarInt(new UVarInt(compactArray.values().length));

      for (var value : compactArray.values()) {
        // TODO
      }

      return baos.toByteArray();
    } catch (IOException e) {
      return null;
    }
  }

  @Override
  public byte[] visitTagBuffer(TagBuffer tagBuffer) {
    try (
      var baos = new ByteArrayOutputStream();
    ) {
      visitUVarInt(new UVarInt(tagBuffer.tags().size()));

      for (var tag : tagBuffer.tags()) {
        // TODO
      }


      return baos.toByteArray();
    } catch (IOException e) {
      return null;
    }
  }

  private byte[] encodeVarInt(int value) {
    var valueBits = Integer.toBinaryString(value);
    var valueBytes = new byte[valueBits.length() / 7 + (valueBits.length() % 7 == 0 ? 0 : 1)];

    for (int i = valueBits.length() - 1, j = 0; i >= 0; i -= 7, j++) {
      var isLast = i < 7;

      var byteBits = (isLast ? "0" : "1") + valueBits.substring(Math.max(0, i - 6), i + 1);
      assert byteBits.length() == 8;

      valueBytes[j] = Byte.parseByte(byteBits, 2);
    }

    return valueBytes;
  }
}
