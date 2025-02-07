package types;

public record Int16(int value) implements KafkaPrimitiveType {
  @Override
  public <T> T accept(KafkaPrimitiveTypeVisitor<T> visitor) {
    return visitor.visitInt16(this);
  }
}
