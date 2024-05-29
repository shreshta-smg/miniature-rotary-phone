package tech.reactivemedia.paymentfmtsvc.core.models;

import tech.reactivemedia.paymentfmtsvc.core.events.PaymentRequestInitiated;
import tech.reactivemedia.paymentfmtsvc.core.ports.spi.PaymentRepository;

import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.util.UUID;

public class PaymentFactory {
    private final PaymentRepository paymentRepository;

    public PaymentFactory(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    private void initiatePayment(Payment payment) {
        payment.setPaymentStatus(PaymentStatus.IN_REVIEW);
        var eventId = UUID.randomUUID();
        var dEvt = new PaymentRequestInitiated(eventId, payment.getPaymentId(),
                payment.getOrderId(),
                payment.getCustomerId(),
                payment.getAmountDetails(),
                payment.getPaymentRef(),
                payment.getPaymentStatus(),
                Instant.now());
        payment.registerEvent(dEvt);
    }

    public Payment createNewPayment(String paymentId,
                                    String customerId,
                                    String orderId,
                                    BigDecimal totalAmount,
                                    BigDecimal amountPaid,
                                    String paymentRefUrl) throws NoSuchAlgorithmException, MalformedURLException {
        var payment =  Payment
                .createPayment(PaymentId.createPaymentId(paymentId),
                        OrderId.createOrderId(orderId),
                        CustomerId.createCustomerId(customerId),
                        AmountDef.createAmountDetail(totalAmount, amountPaid),
                        PaymentRef.createPaymentRef(paymentRefUrl));
        initiatePayment(payment);
        return payment;
    }

}
