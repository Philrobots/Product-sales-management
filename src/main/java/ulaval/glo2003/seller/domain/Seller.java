package ulaval.glo2003.seller.domain;

import ulaval.glo2003.product.domain.Product;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Objects;
import java.util.List;

public class Seller {
  private final SellerId sellerId;
  private final String name;
  private final String bio;
  private final LocalDate birthDate;
  private final Instant createdAt;
  private List<Product> products;

  public Seller(
          SellerId sellerId,
          String name,
          String bio,
          LocalDate birthDate,
          Instant createdAt
  ) {
    this.sellerId = sellerId;
    this.name = name;
    this.bio = bio;
    this.birthDate = birthDate;
    this.createdAt = createdAt;
  }

  public SellerId getSellerId() {
    return this.sellerId;
  }

  public String getStringSellerId() {
    return this.sellerId.toString();
  }

  public String getStringId() {
    return this.sellerId.toString();
  }

  public String getName() {
    return this.name;
  }

  public String getStringCreatedAt() {
    return this.createdAt.toString();
  }

  public String getBio() {
    return this.bio;
  }

  public List<Product> getProducts() {
    return this.products;
  }

  public LocalDate getBirthDate() {
    return this.birthDate;
  }

  public String getStringBirthDate() {
    return this.birthDate.toString();
  }

  public Instant getCreatedAt() {
    return this.createdAt;
  }

  public void setProducts(List<Product> products) {
    this.products = products;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Seller seller = (Seller) o;
    return sellerId.equals(seller.sellerId) && name.equals(seller.name)
            && bio.equals(seller.bio) && birthDate.equals(seller.birthDate)
            && createdAt.equals(seller.createdAt) && products.equals(seller.products);
  }

  @Override
  public int hashCode() {
    return Objects.hash(sellerId, name, bio, birthDate, createdAt, products);
  }
}
