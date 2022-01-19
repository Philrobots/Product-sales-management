package ulaval.glo2003.seller.domain;

import lombok.EqualsAndHashCode;
import ulaval.glo2003.util.UUIDGenerator;

import java.util.UUID;

@EqualsAndHashCode
public class SellerId {
    private final UUID id;

    public SellerId() {
        this.id = UUIDGenerator.generate();
    }

    public SellerId(String sellerId) {
        this.id = UUID.fromString(sellerId);
    }

    public String toString() {
        return this.id.toString();
    }
}