package tech.reactivemedia.paymentfmtsvc.core.models;

import tech.reactivemedia.paymentfmtsvc.core.events.PaymentStatusChanged;
import tech.reactivemedia.paymentfmtsvc.core.shared.BaseEntity;
import tech.reactivemedia.paymentfmtsvc.core.shared.RootAggregate;

import java.time.Instant;
import java.util.UUID;

public class Payment extends RootAggregate implements BaseEntity<Payment> {
    private PaymentId paymentId;
    private PaymentStatus paymentStatus;
    private OrderId orderId;
    private CustomerId customerId;
    private AmountDef amountDetails;
    private PaymentRef paymentRef;
    private StaffId staffId;
    private String remarks;

    private Payment() {
    }

    public Payment(PaymentId paymentId,
                   PaymentStatus paymentStatus,
                   OrderId orderId,
                   CustomerId customerId,
                   AmountDef amountDetails,
                   PaymentRef paymentRef,
                   StaffId staffId,
                   String remarks) {
        this.paymentId = paymentId;
        this.paymentStatus = paymentStatus;
        this.orderId = orderId;
        this.customerId = customerId;
        this.amountDetails = amountDetails;
        this.paymentRef = paymentRef;
        this.staffId = staffId;
        this.remarks = remarks;
    }

    private Payment(PaymentId paymentId,
                    OrderId orderId,
                    CustomerId customerId,
                    AmountDef amountDetails,
                    PaymentRef paymentRef) {
        this.paymentId = paymentId;
        this.orderId = orderId;
        this.customerId = customerId;
        this.amountDetails = amountDetails;
        this.paymentRef = paymentRef;
        paymentStatus = PaymentStatus.IN_REVIEW;
    }

    public static Payment createPayment(PaymentId paymentId,
                                         OrderId orderId,
                                         CustomerId customerId,
                                         AmountDef amountDetails,
                                         PaymentRef paymentRef) {
        return new Payment(paymentId,
                orderId,
                customerId,
                amountDetails,
                paymentRef);
    }

    @Override
    public boolean sameIdentityAs(Payment other) {
        return other != null && paymentId.sameValueAs(other.paymentId);
    }

    public void approvePayment() {
        paymentStatus = PaymentStatus.APPROVED;
        processPayment(paymentStatus);
    }

    public void cancelPayment() {
        paymentStatus = PaymentStatus.CANCELLED;
        processPayment(paymentStatus);
    }

    public void reviewPayment() {
        paymentStatus = PaymentStatus.IN_REVIEW;
        processPayment(paymentStatus);
    }

    public void rejectPayment() {
        paymentStatus = PaymentStatus.REJECTED;
        processPayment(paymentStatus);
    }

    private void processPayment(PaymentStatus paymentStatus) {
        var eventId = UUID.randomUUID();
        var dEvt = new PaymentStatusChanged(eventId,
                paymentId,
                paymentStatus,
                remarks,
                staffId,
                Instant.now());
        registerEvent(dEvt);
    }

    public PaymentId getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(PaymentId paymentId) {
        this.paymentId = paymentId;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public OrderId getOrderId() {
        return orderId;
    }

    public void setOrderId(OrderId orderId) {
        this.orderId = orderId;
    }

    public CustomerId getCustomerId() {
        return customerId;
    }

    public void setCustomerId(CustomerId customerId) {
        this.customerId = customerId;
    }

    public AmountDef getAmountDetails() {
        return amountDetails;
    }

    public void setAmountDetails(AmountDef amountDetails) {
        this.amountDetails = amountDetails;
    }

    public PaymentRef getPaymentRef() {
        return paymentRef;
    }

    public void setPaymentRef(PaymentRef paymentRef) {
        this.paymentRef = paymentRef;
    }

    public StaffId getStaffId() {
        return staffId;
    }

    public void setStaffId(StaffId staffId) {
        this.staffId = staffId;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
