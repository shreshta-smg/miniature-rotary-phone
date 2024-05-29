package tech.reactivemedia.paymentfmtsvc.core.events;

import tech.reactivemedia.paymentfmtsvc.core.models.*;
import tech.reactivemedia.paymentfmtsvc.core.shared.DomainEvent;

import java.time.Instant;
import java.util.UUID;

public record PaymentRequestInitiated(UUID eventId,
                                      PaymentId paymentId,
                                      OrderId orderId,
                                      CustomerId customerId,
                                      AmountDef amountDetails,
                                      PaymentRef paymentRef,
                                      PaymentStatus paymentStatus,
                                      Instant occurredOn) implements DomainEvent {

}
