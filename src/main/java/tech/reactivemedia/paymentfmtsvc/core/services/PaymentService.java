package tech.reactivemedia.paymentfmtsvc.core.services;

import tech.reactivemedia.paymentfmtsvc.core.models.AmountDef;
import tech.reactivemedia.paymentfmtsvc.core.models.Payment;
import tech.reactivemedia.paymentfmtsvc.core.models.PaymentId;
import tech.reactivemedia.paymentfmtsvc.core.models.StaffId;
import tech.reactivemedia.paymentfmtsvc.core.ports.spi.EventBus;
import tech.reactivemedia.paymentfmtsvc.core.ports.spi.PaymentRepository;

import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.security.NoSuchAlgorithmException;

public class PaymentService {
    private final EventBus eventBus;
    private final PaymentRepository paymentRepository;

    public PaymentService(EventBus eventBus, PaymentRepository paymentRepository) {
        this.eventBus = eventBus;
        this.paymentRepository = paymentRepository;
    }

    public Payment updateBalance(PaymentId paymentId, BigDecimal amountPaid) throws MalformedURLException, NoSuchAlgorithmException {
        Payment foundpayment = paymentRepository.findByPaymentId(paymentId.getId());
        if(BigDecimal.ZERO.equals(foundpayment.getAmountDetails().getBalance())) {
            throw new IllegalArgumentException("Balance is already zero.");
        }
        foundpayment.reviewPayment();
        foundpayment.setRemarks("Submitted again for reviewing Payment with id " + paymentId.getId());
        var amountPayment = amountPaid.compareTo(foundpayment.getAmountDetails().getBalance()) < 0 ? amountPaid : amountPaid.subtract(amountPaid.subtract(foundpayment.getAmountDetails().getBalance()));
        foundpayment.setAmountDetails(AmountDef
                .createAmountDetail(foundpayment.getAmountDetails().getTotalAmount(),
                        foundpayment.getAmountDetails().getAmountPaid()
                                .add(amountPayment)
                ));
        paymentRepository.update(foundpayment);
        eventBus.publish(foundpayment.domainEvents());
        return foundpayment;
    }

    public Payment approvePayment(PaymentId paymentId, StaffId staffId) throws MalformedURLException, NoSuchAlgorithmException {
        Payment foundpayment = paymentRepository.findByPaymentId(paymentId.getId());
        foundpayment.approvePayment();
        foundpayment.setRemarks("Approved Payment with id " + paymentId.getId());
        foundpayment.setStaffId(staffId);
        paymentRepository.update(foundpayment);
        eventBus.publish(foundpayment.domainEvents());
        return foundpayment;
    }

    public Payment rejectPayment(PaymentId paymentId, StaffId staffId) throws MalformedURLException, NoSuchAlgorithmException {
        Payment foundpayment = paymentRepository.findByPaymentId(paymentId.getId());
        foundpayment.rejectPayment();
        foundpayment.setRemarks("Rejected Payment with id " + paymentId.getId());
        foundpayment.setStaffId(staffId);
        paymentRepository.update(foundpayment);
        eventBus.publish(foundpayment.domainEvents());
        return foundpayment;
    }

    public Payment cancelPayment(PaymentId paymentId, StaffId staffId) throws MalformedURLException, NoSuchAlgorithmException {
        Payment foundpayment = paymentRepository.findByPaymentId(paymentId.getId());
        foundpayment.cancelPayment();
        foundpayment.setRemarks("Cancelled Payment with id " + paymentId.getId());
        foundpayment.setStaffId(staffId);
        paymentRepository.update(foundpayment);
        eventBus.publish(foundpayment.domainEvents());
        return foundpayment;
    }

    public Payment reviewPayment(PaymentId paymentId, StaffId staffId) throws MalformedURLException, NoSuchAlgorithmException {
        Payment foundpayment = paymentRepository.findByPaymentId(paymentId.getId());
        foundpayment.reviewPayment();
        foundpayment.setRemarks("Reviewing Payment with id " + paymentId.getId());
        foundpayment.setStaffId(staffId);
        paymentRepository.update(foundpayment);
        eventBus.publish(foundpayment.domainEvents());
        return foundpayment;
    }
}
