package tech.reactivemedia.paymentfmtsvc.infra.dao;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import tech.reactivemedia.paymentfmtsvc.core.models.*;
import tech.reactivemedia.paymentfmtsvc.core.ports.spi.PaymentRepository;

import java.net.MalformedURLException;
import java.util.List;

@ApplicationScoped
public class PaymentDaoImpl implements PaymentRepository {
    private final PaymentPanacheRepository paymentPanacheRepository;

    public PaymentDaoImpl(PaymentPanacheRepository paymentPanacheRepository) {
        this.paymentPanacheRepository = paymentPanacheRepository;
    }

    @Override
    public List<Payment> getAll() {
        return paymentPanacheRepository.findAll().stream().map(p -> {
            try {
                return new Payment(
                        PaymentId.getPaymentId(p.getPaymentId()),
                        p.getPaymentStatus(),
                        OrderId.getOrderId(p.getOrderId()),
                        CustomerId.getCustomerId(p.getCustomerId()),
                        AmountDef.createAmountDetail(p.getTotalAmount(), p.getAmountPaid()),
                        PaymentRef.createPaymentRef(p.getPaymentRef()),
                        StaffId.getStaffId(p.getStaffId()),
                        p.getRemarks()
                );
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            }
        }).toList();
    }

    @Override
    public Payment findByPaymentId(String paymentId) throws MalformedURLException {
        var foundPayment = paymentPanacheRepository.findByIdOptional(paymentId).orElseThrow(() -> new DboException("paymentId not found."));
        return new Payment(
                PaymentId.getPaymentId(foundPayment.getPaymentId()),
                foundPayment.getPaymentStatus(),
                OrderId.getOrderId(foundPayment.getOrderId()),
                CustomerId.getCustomerId(foundPayment.getCustomerId()),
                AmountDef.createAmountDetail(foundPayment.getTotalAmount(), foundPayment.getAmountPaid()),
                PaymentRef.createPaymentRef(foundPayment.getPaymentRef()),
                StaffId.getStaffId(foundPayment.getStaffId()),
                foundPayment.getRemarks()
        );
    }

    @Override
    @Transactional
    public void save(Payment prop) {
        var paymentToSave = new PaymentEntity(prop.getPaymentId().getId(),
                prop.getPaymentStatus(), prop.getOrderId().getId(), prop.getCustomerId().getId(),
                prop.getAmountDetails().getTotalAmount(), prop.getAmountDetails().getAmountPaid(),
                prop.getAmountDetails().getBalance(), prop.getPaymentRef().getPaymentRefUrl(),
                prop.getStaffId().getId(), prop.getRemarks());
        paymentPanacheRepository.persist(paymentToSave);
    }

    @Override
    @Transactional
    public void update(Payment prop) {
        var foundPayment = paymentPanacheRepository.findByIdOptional(prop.getPaymentId().getId())
                .orElseThrow(() -> new DboException("No Payment found for Id: [%s]", prop.getPaymentId()));
        foundPayment.setPaymentStatus(prop.getPaymentStatus());
        foundPayment.setPaymentRef(prop.getPaymentRef().getPaymentRefUrl());
        foundPayment.setAmountPaid(prop.getAmountDetails().getAmountPaid());
        foundPayment.setTotalAmount(prop.getAmountDetails().getTotalAmount());
        foundPayment.setBalance(prop.getAmountDetails().getBalance());
        foundPayment.setOrderId(prop.getOrderId().getId());
        foundPayment.setCustomerId(prop.getCustomerId().getId());
        foundPayment.setStaffId(prop.getStaffId().getId());
        foundPayment.setRemarks(prop.getRemarks());
        foundPayment.setPaymentId(prop.getPaymentId().getId());
        paymentPanacheRepository.persist(foundPayment);
    }

    @Override
    @Transactional
    public void delete(String id) {
        paymentPanacheRepository.deleteById(id);
    }
}
