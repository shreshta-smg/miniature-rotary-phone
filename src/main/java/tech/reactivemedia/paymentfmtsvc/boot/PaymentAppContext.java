package tech.reactivemedia.paymentfmtsvc.boot;

import jakarta.enterprise.inject.Produces;
import tech.reactivemedia.paymentfmtsvc.app.ports.api.PaymentAPIService;
import tech.reactivemedia.paymentfmtsvc.app.services.PaymentAPIServiceImpl;
import tech.reactivemedia.paymentfmtsvc.core.models.PaymentFactory;
import tech.reactivemedia.paymentfmtsvc.core.ports.spi.EventBus;
import tech.reactivemedia.paymentfmtsvc.core.ports.spi.PaymentRepository;
import tech.reactivemedia.paymentfmtsvc.core.services.PaymentService;

public class PaymentAppContext {
    @Produces
    public PaymentFactory paymentFactory(PaymentRepository paymentRepository) {
        return new PaymentFactory(paymentRepository);
    }

    @Produces
    public PaymentAPIService paymentAPIService(PaymentRepository paymentRepository,
                                               EventBus eventBus,
                                               PaymentFactory paymentFactory, PaymentService paymentService) {
        return new PaymentAPIServiceImpl(paymentRepository, paymentFactory, eventBus, paymentService);
    }

    @Produces
    public PaymentService paymentService(PaymentRepository paymentRepository, EventBus eventBus) {
        return new PaymentService(eventBus, paymentRepository);
    }
}
