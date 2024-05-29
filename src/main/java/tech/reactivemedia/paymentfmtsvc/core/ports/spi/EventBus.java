package tech.reactivemedia.paymentfmtsvc.core.ports.spi;

import tech.reactivemedia.paymentfmtsvc.core.shared.DomainEvent;

import java.util.Collection;

public interface EventBus {
    void publish(final Collection<DomainEvent> evts);
}
