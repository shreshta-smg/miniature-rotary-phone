package tech.reactivemedia.paymentfmtsvc.infra.rest.requests;

import java.math.BigDecimal;

public record UpdateBalanceRequest(String paymentId, BigDecimal amountPaid) {
}
