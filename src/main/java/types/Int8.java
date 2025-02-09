package types;

import bytes.ToBytes;

public record Int8(int value) implements ToBytes {
  @Override
  public byte[] toBytes() {
    return new byte[]{(byte) (value & 0xFF)};
  }
}
