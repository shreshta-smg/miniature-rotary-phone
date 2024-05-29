package tech.reactivemedia.paymentfmtsvc.infra.dao;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PaymentPanacheRepository implements PanacheRepositoryBase<PaymentEntity, String> {
}
