package tech.reactivemedia.paymentfmtsvc.infra.rest.requests;

import tech.reactivemedia.paymentfmtsvc.core.models.PaymentStatus;

public record ChangePaymentStatusRequest(String paymentId, PaymentStatus paymentStatus, String staffId) {
}
