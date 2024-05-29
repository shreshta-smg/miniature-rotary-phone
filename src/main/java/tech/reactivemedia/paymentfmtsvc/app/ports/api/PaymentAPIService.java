package tech.reactivemedia.paymentfmtsvc.app.ports.api;

import tech.reactivemedia.paymentfmtsvc.core.models.Payment;
import tech.reactivemedia.paymentfmtsvc.core.models.PaymentId;

import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public interface PaymentAPIService {
    Payment getPaymentByPaymentId(String id) throws MalformedURLException, NoSuchAlgorithmException;

    List<Payment> getAllPayments();

    void deletePayment(String paymentId);

    void create(String paymentId,
                String customerId,
                String orderId,
                BigDecimal totalAmount,
                BigDecimal amountPaid,
                String paymentRefUrl) throws MalformedURLException, NoSuchAlgorithmException;

    void update(Payment prop);

    Payment approvePayment(String paymentId, String staffId) throws MalformedURLException, NoSuchAlgorithmException;

    Payment rejectPayment(String paymentId, String staffId) throws MalformedURLException, NoSuchAlgorithmException;

    Payment cancelPayment(String paymentId, String staffId) throws MalformedURLException, NoSuchAlgorithmException;

    Payment reviewPayment(String paymentId, String staffId) throws MalformedURLException, NoSuchAlgorithmException;
    Payment updateBalance(String paymentId, BigDecimal amountPaid) throws MalformedURLException, NoSuchAlgorithmException;
}
