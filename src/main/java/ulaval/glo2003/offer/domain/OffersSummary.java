package ulaval.glo2003.offer.domain;

import ulaval.glo2003.main.domain.Amount;

import java.util.Objects;

public class OffersSummary {

  private Amount mean;
  private Integer count;
  private Amount min;
  private Amount max;

  public OffersSummary() {
    this.count = 0;
    this.mean = null;
    this.min = null;
    this.max = null;
  }

  public OffersSummary(Amount mean, Integer count, Amount min, Amount max) {
    this.mean = mean;
    this.count = count;
    this.min = min;
    this.max = max;
  }

  public Double getMeanAmount() {
    if (this.mean == null) {
      return null;
    }
    return this.mean.getDoubleValue();
  }

  public Integer getCount() {
    return this.count;
  }

  public Double getMaxAmount() {
    if (this.max == null) {
      return null;
    }
    return this.max.getDoubleValue();
  }

  public Double getMinAmount() {
    if (this.min == null) {
      return null;
    }
    return this.min.getDoubleValue();
  }

  public void addOfferAmount(Amount offerAmount) {
    if (this.max == null || offerAmount.isHigher(this.max)) {
      this.max = offerAmount;
    }

    if (this.min == null || this.min.isHigher(offerAmount)) {
      this.min = offerAmount;
    }

    if (this.mean != null) {
      Amount totalAMountOfOffers = this.mean.multiply(this.count).add(offerAmount);
      this.count += 1;
      this.mean = totalAMountOfOffers.divide(this.count);
    } else {
      this.mean = offerAmount;
      this.count += 1;
    }
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    OffersSummary that = (OffersSummary) o;
    return Objects.equals(mean, that.mean)
            && count.equals(that.count)
            && Objects.equals(min, that.min)
            && Objects.equals(max, that.max);
  }

  @Override
  public int hashCode() {
    return Objects.hash(mean, count, min, max);
  }
}
