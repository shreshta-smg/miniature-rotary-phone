package tech.reactivemedia.paymentfmtsvc.core.models;

import tech.reactivemedia.paymentfmtsvc.core.shared.ValueObject;
import tech.reactivemedia.paymentfmtsvc.core.utils.ValidatorUtils;

import java.net.MalformedURLException;

public class PaymentRef implements ValueObject<PaymentRef> {
    private final String paymentRefUrl;

    private PaymentRef(String paymentRefUrl) throws MalformedURLException {
        ValidatorUtils.isValidURL(paymentRefUrl);
        this.paymentRefUrl = paymentRefUrl;
    }

    public static PaymentRef createPaymentRef(String paymentRefUrl) throws MalformedURLException {
        return new PaymentRef(paymentRefUrl);
    }

    @Override
    public boolean sameValueAs(PaymentRef other) {
        return other != null && this.paymentRefUrl.equals(other.paymentRefUrl);
    }

    public String getPaymentRefUrl() {
        return paymentRefUrl;
    }

    @Override
    public String toString() {
        return "PaymentRef{" +
                "paymentRefUrl='" + paymentRefUrl + '\'' +
                '}';
    }
}
