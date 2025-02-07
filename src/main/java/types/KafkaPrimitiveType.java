package types;

public sealed interface KafkaPrimitiveType permits Int16, Int32, NullableString, CompactArray {
  <T> T accept(KafkaPrimitiveTypeVisitor<T> visitor);
}
