package tech.reactivemedia.paymentfmtsvc.infra.adapters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.inject.Singleton;
import org.jboss.logging.Logger;
import tech.reactivemedia.paymentfmtsvc.core.ports.spi.EventBus;
import tech.reactivemedia.paymentfmtsvc.core.shared.DomainEvent;

import java.util.Collection;

@Singleton
public class EventBusImpl implements EventBus {
    private static final Logger log = Logger.getLogger(EventBusImpl.class);
    private final io.vertx.core.eventbus.EventBus bus;

    public EventBusImpl(io.vertx.core.eventbus.EventBus bus) {
        this.bus = bus;
    }

    @Override
    public void publish(Collection<DomainEvent> evts) {
        ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
        var publishedEvents = evts.stream().map(evt -> {
            try {
                return bus.publish("payments", objectMapper.writeValueAsString(evt));
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }).toList();
        log.debug("publishedEvents: " +  publishedEvents);
    }
}
