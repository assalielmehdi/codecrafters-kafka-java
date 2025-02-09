package types;

import java.util.List;

public record TagBuffer(List<Tag> tags) implements KafkaPrimitiveType {
  public record Tag(int key, byte[] value) {
  }

  @Override
  public <T> T accept(KafkaPrimitiveTypeVisitor<T> visitor) {
    return visitor.visitTagBuffer(this);
  }

  public static TagBuffer EMPTY = new TagBuffer(List.of());
}
