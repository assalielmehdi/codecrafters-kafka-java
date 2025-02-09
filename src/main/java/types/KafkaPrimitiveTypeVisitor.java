package types;

public interface KafkaPrimitiveTypeVisitor<T> {
  T visitInt16(Int16 int16);

  T visitInt32(Int32 int32);

  T visitVarInt(VarInt varInt);

  T visitUVarInt(UVarInt uVarInt);

  T visitNullableString(NullableString nullableString);

  <V> T visitCompactArray(CompactArray<V> compactArray);

  T visitTagBuffer(TagBuffer tagBuffer);
}
