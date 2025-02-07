package types;

public record NullableString(String value) implements KafkaPrimitiveType {
  @Override
  public <T> T accept(KafkaPrimitiveTypeVisitor<T> visitor) {
    return visitor.visitNullableString(this);
  }
}
