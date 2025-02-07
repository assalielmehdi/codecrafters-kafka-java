package types;

public record CompactArray(Object[] values) implements KafkaPrimitiveType{
  @Override
  public <T> T accept(KafkaPrimitiveTypeVisitor<T> visitor) {
    return visitor.visitCompactArray(this);
  }
}
