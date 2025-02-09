package types;

import bytes.ToBytes;

import java.util.Arrays;

public record CompactArray<V extends ToBytes>(V[] values) implements ToBytes {
  @Override
  public byte[] toBytes() {
    return ToBytes.join(
      new Int8(values.length).toBytes(),
      Arrays.stream(values)
        .map(ToBytes::toBytes)
        .reduce(new byte[0], ToBytes::join)
    );
  }
}
