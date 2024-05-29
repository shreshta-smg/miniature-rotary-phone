package tech.reactivemedia.paymentfmtsvc.infra.rest.mapper;

import tech.reactivemedia.paymentfmtsvc.core.models.AmountDef;
import tech.reactivemedia.paymentfmtsvc.core.models.CustomerId;
import tech.reactivemedia.paymentfmtsvc.core.models.OrderId;
import tech.reactivemedia.paymentfmtsvc.core.models.PaymentId;
import tech.reactivemedia.paymentfmtsvc.core.models.PaymentRef;
import tech.reactivemedia.paymentfmtsvc.core.models.PaymentStatus;
import tech.reactivemedia.paymentfmtsvc.core.models.StaffId;

public record PaymentDTO(
                         PaymentId paymentId,
                         OrderId orderId,
                         CustomerId customerId,
                         AmountDef amountDetails,
                         PaymentRef paymentRef,
                         PaymentStatus paymentStatus,
                         String remarks,
                         StaffId staffId) {
}
