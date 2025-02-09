package bytes;

import java.util.Arrays;

public interface ToBytes {
  byte[] toBytes();

  static byte[] join(byte[] ...bytesArray) {
    int bytesLength = Arrays.stream(bytesArray).mapToInt(bytes -> bytes.length).sum();
    var bytes = new byte[bytesLength];

    for (int i = 0, offset = 0; i < bytesArray.length; offset += bytesArray[i].length, i++) {
      System.arraycopy(bytesArray[i], 0, bytes, offset, bytesArray[i].length);
    }

    return bytes;
  }
}
