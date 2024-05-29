package tech.reactivemedia.paymentfmtsvc.infra.rest.requests;

import java.math.BigDecimal;

public record InitiatePaymentRequest(String paymentId,
                                     String orderId,
                                     String customerId,
                                     BigDecimal totalAmount,
                                     BigDecimal amountPaid,
                                     String paymentRef) {
}
