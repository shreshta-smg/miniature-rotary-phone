package tech.reactivemedia.paymentfmtsvc.infra.dao;

import jakarta.persistence.*;
import tech.reactivemedia.paymentfmtsvc.core.models.*;

import java.math.BigDecimal;

@Entity
@Table(name = "payments")
public class PaymentEntity {
    @Id
    private String paymentId;
    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;
    private String orderId;
    private String customerId;
    @Column(precision = 10, scale = 2)
    private BigDecimal totalAmount;
    @Column(precision = 10, scale = 2)
    private BigDecimal amountPaid;
    @Column(precision = 10, scale = 2)
    private BigDecimal balance;
    private String paymentRef;
    private String staffId;
    private String remarks;

    public PaymentEntity(String paymentId, PaymentStatus paymentStatus, String orderId, String customerId, BigDecimal totalAmount, BigDecimal amountPaid, BigDecimal balance, String paymentRef, String staffId, String remarks) {
        this.paymentId = paymentId;
        this.paymentStatus = paymentStatus;
        this.orderId = orderId;
        this.customerId = customerId;
        this.totalAmount = totalAmount;
        this.amountPaid = amountPaid;
        this.balance = balance;
        this.paymentRef = paymentRef;
        this.staffId = staffId;
        this.remarks = remarks;
    }

    public PaymentEntity() {
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public BigDecimal getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(BigDecimal amountPaid) {
        this.amountPaid = amountPaid;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getPaymentRef() {
        return paymentRef;
    }

    public void setPaymentRef(String paymentRef) {
        this.paymentRef = paymentRef;
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
