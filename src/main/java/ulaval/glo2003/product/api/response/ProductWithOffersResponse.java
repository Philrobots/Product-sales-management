package ulaval.glo2003.product.api.response;

import ulaval.glo2003.offer.api.response.OffersInformationResponse;

import java.util.List;

public class ProductWithOffersResponse {
  public String id;
  public String title;
  public String description;
  public Double suggestedPrice;
  public String createdAt;
  public List<String> categories;
  public OffersInformationResponse offers;

  public ProductWithOffersResponse() {
  }

  public ProductWithOffersResponse(String id, String title, String description, Double suggestedPrice,
                                   List<String> categories, OffersInformationResponse offersInformationResponse,
                                   String createdAt) {
    this.id = id;
    this.title = title;
    this.description = description;
    this.suggestedPrice = suggestedPrice;
    this.categories = categories;
    this.offers = offersInformationResponse;
    this.createdAt = createdAt;
  }
}
