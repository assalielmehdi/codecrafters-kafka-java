package types;

public record CompactArray<V>(V[] values) implements KafkaPrimitiveType{
  @Override
  public <T> T accept(KafkaPrimitiveTypeVisitor<T> visitor) {
    return visitor.visitCompactArray(this);
  }
}
