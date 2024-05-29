package tech.reactivemedia.paymentfmtsvc.core.shared;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public abstract class RootAggregate {
    private final List<DomainEvent> domainEvents = new ArrayList<>();
    private final Logger logger = LoggerFactory.getLogger(RootAggregate.class);

    public void registerEvent(DomainEvent evt) {
        logger.debug("registerEvent({})", evt);
        domainEvents.add(evt);
    }

    public void clearDomainEvents() {
        logger.debug("clearDomainEvents()");
        domainEvents.clear();
    }

    public Collection<DomainEvent> domainEvents() {
        logger.debug("domainEvents()");
        return Collections.unmodifiableList(domainEvents);
    }
}
