package types;

public record VarInt(int value) implements KafkaPrimitiveType {
  @Override
  public <T> T accept(KafkaPrimitiveTypeVisitor<T> visitor) {
    return visitor.visitVarInt(this);
  }
}
