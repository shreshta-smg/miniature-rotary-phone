package tech.reactivemedia.paymentfmtsvc.core.models;

import tech.reactivemedia.paymentfmtsvc.core.shared.ValueObject;
import tech.reactivemedia.paymentfmtsvc.core.utils.HashUtils;

import java.security.NoSuchAlgorithmException;
import java.util.UUID;

public class OrderId implements ValueObject<OrderId> {
    private final String id;

    private OrderId(String id) {
        this.id = id;
    }

    public static OrderId createOrderId(String id) throws NoSuchAlgorithmException {
        var newId = UUID.fromString(id);
        return new OrderId(HashUtils.sha1WithBase64(newId.toString()));
    }

    public static OrderId getOrderId(String id) {
        return new OrderId(id);
    }

    @Override
    public boolean sameValueAs(OrderId other) {
        return other != null && this.id.equals(other.id);
    }

    public String getId() {
        return id;
    }

}
