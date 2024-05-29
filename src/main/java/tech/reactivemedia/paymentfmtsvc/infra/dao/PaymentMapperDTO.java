package tech.reactivemedia.paymentfmtsvc.infra.dao;

import tech.reactivemedia.paymentfmtsvc.core.models.*;

import java.math.BigDecimal;

public record PaymentMapperDTO(
        String paymentId,
        String orderId,
        String customerId,
        BigDecimal totalAmount,
        BigDecimal amountPaid,
        BigDecimal balance,
        String paymentRef,
        PaymentStatus paymentStatus,
        String remarks,
        String staffId) {
}
