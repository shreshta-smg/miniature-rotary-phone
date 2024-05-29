package tech.reactivemedia.paymentfmtsvc.core.events;

import tech.reactivemedia.paymentfmtsvc.core.models.PaymentId;
import tech.reactivemedia.paymentfmtsvc.core.models.PaymentStatus;
import tech.reactivemedia.paymentfmtsvc.core.models.StaffId;
import tech.reactivemedia.paymentfmtsvc.core.shared.DomainEvent;

import java.time.Instant;
import java.util.UUID;

public record PaymentStatusChanged(UUID eventId,
                                   PaymentId paymentId,
                                   PaymentStatus paymentStatus,
                                   String remarks,
                                   StaffId staffId,
                                   Instant occurredOn) implements DomainEvent {
}
