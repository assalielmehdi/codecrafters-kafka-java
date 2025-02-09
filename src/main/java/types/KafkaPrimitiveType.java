package types;

public sealed interface KafkaPrimitiveType permits Int16, Int32, VarInt, UVarInt, NullableString, CompactArray, TagBuffer{
  <T> T accept(KafkaPrimitiveTypeVisitor<T> visitor);
}
