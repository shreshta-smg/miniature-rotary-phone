package tech.reactivemedia.paymentfmtsvc.core.models;

import tech.reactivemedia.paymentfmtsvc.core.shared.ValueObject;
import tech.reactivemedia.paymentfmtsvc.core.utils.HashUtils;

import java.security.NoSuchAlgorithmException;
import java.util.UUID;

public class PaymentId implements ValueObject<PaymentId> {

    private final String id;

    private PaymentId(String id) {
        this.id = id;
    }

    public static PaymentId createPaymentId(String id) throws NoSuchAlgorithmException {
        var newId = UUID.fromString(id);
        return new PaymentId(HashUtils.sha1WithBase64(newId.toString()));
    }

    public static PaymentId getPaymentId(String id) {
        return new PaymentId(id);
    }

    @Override
    public boolean sameValueAs(PaymentId other) {
        return other != null && this.id.equals(other.id);
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "PaymentId{" +
                "id='" + id + '\'' +
                '}';
    }
}
