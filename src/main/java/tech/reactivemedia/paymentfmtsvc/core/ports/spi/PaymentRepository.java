package tech.reactivemedia.paymentfmtsvc.core.ports.spi;

import tech.reactivemedia.paymentfmtsvc.core.models.Payment;

import java.net.MalformedURLException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public interface PaymentRepository {
    List<Payment> getAll();

    Payment findByPaymentId(String paymentId) throws NoSuchAlgorithmException, MalformedURLException;

    void save(Payment prop);

    void update(Payment prop);

    void delete(String id);

}
