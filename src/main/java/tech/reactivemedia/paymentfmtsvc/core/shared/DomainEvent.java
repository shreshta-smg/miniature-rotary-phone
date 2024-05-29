package tech.reactivemedia.paymentfmtsvc.core.shared;

import java.time.Instant;
import java.util.UUID;

public interface DomainEvent {
    UUID eventId();

    Instant occurredOn();
}
