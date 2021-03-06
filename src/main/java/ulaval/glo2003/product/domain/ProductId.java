package ulaval.glo2003.product.domain;

import ulaval.glo2003.product.domain.exceptions.InvalidProductIdException;
import ulaval.glo2003.util.UUIDGenerator;

import java.util.Objects;
import java.util.UUID;

public class ProductId {
  private final UUID id;

  public ProductId() {
    this.id = UUIDGenerator.generate();
  }

  public ProductId(String productId) throws InvalidProductIdException {
    try {
      this.id = UUID.fromString(productId);
    } catch (Exception e) {
      throw new InvalidProductIdException();
    }
  }

  public String toString() {
    return this.id.toString();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ProductId productId = (ProductId) o;
    return id.equals(productId.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}
