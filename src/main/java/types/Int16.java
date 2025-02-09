package types;

import bytes.ToBytes;

public record Int16(int value) implements ToBytes {
  public static Int16 fromBytes(byte[] bytes) {
    return new Int16(
      (bytes[0] & 0xFF) << 8 |
      bytes[1] & 0xFF
    );
  }

  @Override
  public byte[] toBytes() {
    return new byte[]{
      (byte) ((value >> 8) & 0xFF),
      (byte) (value & 0xFF)
    };
  }
}
