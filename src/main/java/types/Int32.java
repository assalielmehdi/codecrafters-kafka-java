package types;

import bytes.ToBytes;

public record Int32(int value) implements ToBytes {
  public static Int32 fromBytes(byte[] bytes) {
    return new Int32(
      (bytes[0] & 0xFF) << 24 |
      (bytes[1] & 0xFF) << 16 |
      (bytes[2] & 0xFF) << 8 |
      bytes[3] & 0xFF
    );
  }

  @Override
  public byte[] toBytes() {
    return new byte[]{
      (byte) ((value >> 24) & 0xFF),
      (byte) ((value >> 16) & 0xFF),
      (byte) ((value >> 8) & 0xFF),
      (byte) (value & 0xFF)
    };
  }
}
