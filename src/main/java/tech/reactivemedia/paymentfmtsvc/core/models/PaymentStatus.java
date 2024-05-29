package tech.reactivemedia.paymentfmtsvc.core.models;

import tech.reactivemedia.paymentfmtsvc.core.shared.ValueObject;

public enum PaymentStatus implements ValueObject<PaymentStatus> {
    APPROVED("Approved"), IN_REVIEW("InReview"), REJECTED("Rejected"), CANCELLED("Cancelled");

    private final String status;

    PaymentStatus(String status) {
        this.status = status;
    }

    public String value() {
        return status;
    }

    @Override
    public boolean sameValueAs(PaymentStatus other) {
        return this.equals(other);
    }

    @Override
    public String toString() {
        return "PaymentStatus{" +
                "status='" + status + '\'' +
                '}';
    }
}
