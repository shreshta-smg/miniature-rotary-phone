package tech.reactivemedia.paymentfmtsvc.core.shared;

public interface BaseEntity<T> {
    boolean sameIdentityAs(T other);
}
