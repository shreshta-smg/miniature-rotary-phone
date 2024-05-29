package tech.reactivemedia.paymentfmtsvc.app.services;

import tech.reactivemedia.paymentfmtsvc.app.ports.api.PaymentAPIService;
import tech.reactivemedia.paymentfmtsvc.core.models.Payment;
import tech.reactivemedia.paymentfmtsvc.core.models.PaymentFactory;
import tech.reactivemedia.paymentfmtsvc.core.models.PaymentId;
import tech.reactivemedia.paymentfmtsvc.core.models.StaffId;
import tech.reactivemedia.paymentfmtsvc.core.ports.spi.EventBus;
import tech.reactivemedia.paymentfmtsvc.core.ports.spi.PaymentRepository;
import tech.reactivemedia.paymentfmtsvc.core.services.PaymentService;

import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public class PaymentAPIServiceImpl implements PaymentAPIService {

    private final PaymentRepository paymentRepository;
    private final PaymentFactory paymentFactory;
    private final EventBus eventBus;
    private final PaymentService paymentService;

    public PaymentAPIServiceImpl(PaymentRepository paymentRepository, PaymentFactory paymentFactory, EventBus eventBus, PaymentService paymentService) {
        this.paymentRepository = paymentRepository;
        this.paymentFactory = paymentFactory;
        this.eventBus = eventBus;
        this.paymentService = paymentService;
    }

    @Override
    public Payment getPaymentByPaymentId(String id) throws MalformedURLException, NoSuchAlgorithmException {
        return paymentRepository.findByPaymentId(id);
    }

    @Override
    public List<Payment> getAllPayments() {
        return paymentRepository.getAll();
    }

    @Override
    public void deletePayment(String paymentId) {
        paymentRepository.delete(paymentId);
    }

    @Override
    public void create(String paymentId,
                       String customerId,
                       String orderId,
                       BigDecimal totalAmount,
                       BigDecimal amountPaid,
                       String paymentRefUrl) throws MalformedURLException, NoSuchAlgorithmException {
       Payment newPayment = paymentFactory.createNewPayment(paymentId,
                customerId,
                orderId,
                totalAmount,
                amountPaid,
                paymentRefUrl);
       paymentRepository.save(newPayment);
       eventBus.publish(newPayment.domainEvents());

    }

    @Override
    public void update(Payment prop) {
        paymentRepository.update(prop);
    }

    @Override
    public Payment approvePayment(String paymentId, String staffId) throws MalformedURLException, NoSuchAlgorithmException {
        return paymentService.approvePayment(PaymentId.getPaymentId(paymentId),  StaffId.getStaffId(staffId));
    }

    @Override
    public Payment rejectPayment(String paymentId, String staffId) throws MalformedURLException, NoSuchAlgorithmException {
        return paymentService.rejectPayment(PaymentId.getPaymentId(paymentId),  StaffId.getStaffId(staffId));
    }

    @Override
    public Payment cancelPayment(String paymentId, String staffId) throws MalformedURLException, NoSuchAlgorithmException {
        return paymentService.cancelPayment(PaymentId.getPaymentId(paymentId),  StaffId.getStaffId(staffId));
    }

    @Override
    public Payment reviewPayment(String paymentId, String staffId) throws MalformedURLException, NoSuchAlgorithmException {
        return paymentService.reviewPayment(PaymentId.getPaymentId(paymentId), StaffId.getStaffId(staffId));
    }

    public Payment updateBalance(String paymentId, BigDecimal amountPaid) throws MalformedURLException, NoSuchAlgorithmException {
        return paymentService.updateBalance(PaymentId.getPaymentId(paymentId), amountPaid);
    }


}
