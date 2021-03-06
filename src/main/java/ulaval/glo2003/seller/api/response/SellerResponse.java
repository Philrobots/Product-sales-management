package ulaval.glo2003.seller.api.response;


import java.util.Objects;
import java.util.List;

public class SellerResponse {
  public String id;
  public String name;
  public String createdAt;
  public String bio;
  public List<SellerProductResponse> products;

  public SellerResponse() {
  }

  public SellerResponse(String id, String name, String createdAt, String bio, List<SellerProductResponse> products) {
    this.id = id;
    this.name = name;
    this.createdAt = createdAt;
    this.bio = bio;
    this.products = products;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    SellerResponse that = (SellerResponse) o;
    return id.equals(that.id) && name.equals(that.name) && createdAt.equals(that.createdAt) && bio.equals(that.bio);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, createdAt, bio);
  }
}
