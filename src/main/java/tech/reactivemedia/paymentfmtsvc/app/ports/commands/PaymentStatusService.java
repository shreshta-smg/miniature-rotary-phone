package tech.reactivemedia.paymentfmtsvc.app.ports.commands;

import tech.reactivemedia.paymentfmtsvc.core.models.PaymentId;

public interface PaymentStatusService {
    void approvePayment(PaymentId paymentId);
    void reviewPayment(PaymentId paymentId);
    void rejectPayment(PaymentId paymentId);
    void cancelPayment(PaymentId paymentId);
}
