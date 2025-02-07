package types;

public interface KafkaPrimitiveTypeVisitor<T> {
  T visitInt16(Int16 int16);

  T visitInt32(Int32 int32);

  T visitNullableString(NullableString nullableString);

  T visitCompactArray(CompactArray compactArray);
}
