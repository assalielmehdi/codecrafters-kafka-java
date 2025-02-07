package types;

public record Int32(int value) implements KafkaPrimitiveType {
  @Override
  public <T> T accept(KafkaPrimitiveTypeVisitor<T> visitor) {
    return visitor.visitInt32(this);
  }
}
