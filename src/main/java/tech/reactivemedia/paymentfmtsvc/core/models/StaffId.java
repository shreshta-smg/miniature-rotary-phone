package tech.reactivemedia.paymentfmtsvc.core.models;

import tech.reactivemedia.paymentfmtsvc.core.shared.ValueObject;
import tech.reactivemedia.paymentfmtsvc.core.utils.HashUtils;

import java.security.NoSuchAlgorithmException;
import java.util.Objects;
import java.util.UUID;

public class StaffId implements ValueObject<StaffId> {
    private final String id;

    private StaffId(String id) {
        try {
            this.id = Objects.requireNonNullElse(id, "");
        } catch (IllegalArgumentException ex) {
            throw new IllegalArgumentException("Invalid argument passed for StaffID: " + id);
        }
    }

    public static StaffId createStaffId(String id) throws NoSuchAlgorithmException {
        var newId = UUID.fromString(id);
        return new StaffId(HashUtils.sha1WithBase64(newId.toString()));
    }

    public static StaffId getStaffId(String id) {
        return new StaffId(id);
    }

    @Override
    public boolean sameValueAs(StaffId other) {
        return other != null && this.id.equals(other.id);
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "StaffId{" +
                "id='" + id + '\'' +
                '}';
    }
}
