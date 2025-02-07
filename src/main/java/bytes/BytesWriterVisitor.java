package bytes;

import types.*;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

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
    } catch (IOException e
    ) {
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
  public byte[] visitCompactArray(CompactArray compactArray) {
    // TODO
    return null;
  }
}
