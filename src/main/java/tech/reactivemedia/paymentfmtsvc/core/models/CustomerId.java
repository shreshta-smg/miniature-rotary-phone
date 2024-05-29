package tech.reactivemedia.paymentfmtsvc.core.models;

import tech.reactivemedia.paymentfmtsvc.core.shared.ValueObject;
import tech.reactivemedia.paymentfmtsvc.core.utils.HashUtils;

import java.security.NoSuchAlgorithmException;
import java.util.UUID;

public class CustomerId implements ValueObject<CustomerId> {
    private final String id;

    private CustomerId(String id) {
        this.id = id;
    }

    public static CustomerId createCustomerId(String id) throws NoSuchAlgorithmException {
        var newId = UUID.fromString(id);
        return new CustomerId(HashUtils.sha1WithBase64(newId.toString()));
    }

    public static CustomerId getCustomerId(String id) {
        return new CustomerId(id);
    }

    @Override
    public boolean sameValueAs(CustomerId other) {
        return other != null && this.id.equals(other.id);
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "CustomerId{" +
                "id='" + id + '\'' +
                '}';
    }
}
