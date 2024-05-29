package tech.reactivemedia.paymentfmtsvc.core.models;

import tech.reactivemedia.paymentfmtsvc.core.shared.ValueObject;

import java.math.BigDecimal;

public class AmountDef implements ValueObject<AmountDef> {
    private final BigDecimal totalAmount;
    private final BigDecimal amountPaid;
    private final BigDecimal balance;

    private AmountDef(BigDecimal totalAmount, BigDecimal amountPaid) {
        this.totalAmount = totalAmount;
        this.amountPaid = amountPaid;
        balance = totalAmount.subtract(amountPaid);
    }

    public static AmountDef createAmountDetail(BigDecimal totalAmount, BigDecimal amountPaid) {
        return new AmountDef(totalAmount, amountPaid);
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public BigDecimal getAmountPaid() {
        return amountPaid;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    @Override
    public boolean sameValueAs(AmountDef other) {
        return other != null && totalAmount.equals(other.totalAmount) && amountPaid.equals(other.amountPaid);
    }

    public Boolean checkIfValidBalance() {
        return BigDecimal.ZERO.compareTo(getTotalAmount()) < 0 && BigDecimal.ZERO.equals(balance);
    }

    public Boolean checkIfZeroTotalAmount() {
        return BigDecimal.ZERO.equals(getTotalAmount());
    }

    @Override
    public String toString() {
        return "AmountDef{" +
                "totalAmount=" + totalAmount +
                ", amountPaid=" + amountPaid +
                ", balance=" + balance +
                '}';
    }


}
