package ulaval.glo2003.seller.api;

import java.util.Objects;

public class SellerResponse {
    public final String id;
    public final String name;
    public final String createdAt;
    public final String bio;

    public SellerResponse(String id, String name, String createdAt, String bio) {
        this.id = id;
        this.name = name;
        this.createdAt = createdAt;
        this.bio = bio;
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
