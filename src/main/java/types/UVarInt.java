package types;

public record UVarInt(int value) implements KafkaPrimitiveType {
  @Override
  public <T> T accept(KafkaPrimitiveTypeVisitor<T> visitor) {
    return visitor.visitUVarInt(this);
  }
}
