package tech.reactivemedia.paymentfmtsvc.infra.rest.api;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;
import tech.reactivemedia.paymentfmtsvc.app.ports.api.PaymentAPIService;
import tech.reactivemedia.paymentfmtsvc.infra.rest.mapper.PaymentDTO;
import tech.reactivemedia.paymentfmtsvc.infra.rest.requests.ChangePaymentStatusRequest;
import tech.reactivemedia.paymentfmtsvc.infra.rest.requests.GetPaymentByIdQuery;
import tech.reactivemedia.paymentfmtsvc.infra.rest.requests.InitiatePaymentRequest;
import tech.reactivemedia.paymentfmtsvc.infra.rest.requests.UpdateBalanceRequest;

import java.net.MalformedURLException;
import java.net.URI;
import java.security.NoSuchAlgorithmException;

@Path("/api/payments")
public class PaymentResource {

    private final PaymentAPIService paymentAPIService;

    public PaymentResource(PaymentAPIService paymentAPIService) {
        this.paymentAPIService = paymentAPIService;
    }

    @GET
    public Response doGetAllPayments() {
        Response response;
        var payments = paymentAPIService.getAllPayments();
        if(!payments.isEmpty()) {
            response = Response.ok(payments.stream().map(p -> new PaymentDTO(
                    p.getPaymentId(), p.getOrderId(),
                    p.getCustomerId(),
                    p.getAmountDetails(),
                    p.getPaymentRef(), p.getPaymentStatus(),
                    p.getRemarks(), p.getStaffId()
            )).toList()).build();
        } else {
            response = Response.noContent().build();
        }
        return response;
    }

    @POST
    public Response doCreatePayment(InitiatePaymentRequest initPaymentReq) throws MalformedURLException, NoSuchAlgorithmException {
        paymentAPIService.create(initPaymentReq.paymentId(),
                initPaymentReq.customerId(),
                initPaymentReq.orderId(),
                initPaymentReq.totalAmount(),
                initPaymentReq.amountPaid(),
                initPaymentReq.paymentRef());
        return Response.created(URI.create("/payments/" + initPaymentReq.paymentId())).build();
    }

    @PATCH
    @Path("/update-payment-status")
    public Response doChangePaymentStatus(ChangePaymentStatusRequest changePaymentStatusRequest) throws MalformedURLException, NoSuchAlgorithmException {
        var paymentReview = switch(changePaymentStatusRequest.paymentStatus()) {
            case APPROVED -> paymentAPIService.approvePayment(changePaymentStatusRequest.paymentId(), changePaymentStatusRequest.staffId());
            case CANCELLED -> paymentAPIService.cancelPayment(changePaymentStatusRequest.paymentId(), changePaymentStatusRequest.staffId());
            case REJECTED -> paymentAPIService.rejectPayment(changePaymentStatusRequest.paymentId(), changePaymentStatusRequest.staffId());
            default -> paymentAPIService.reviewPayment(changePaymentStatusRequest.paymentId(), changePaymentStatusRequest.staffId());
        };
        var foundPayment = new PaymentDTO(
                paymentReview.getPaymentId(), paymentReview.getOrderId(),
                paymentReview.getCustomerId(),
                paymentReview.getAmountDetails(),
                paymentReview.getPaymentRef(), paymentReview.getPaymentStatus(),
                paymentReview.getRemarks(), paymentReview.getStaffId()
        );
        return Response.accepted(paymentReview).build();
    }

    @PATCH
    @Path("/update-balance")
    public Response doUpdateBalance(UpdateBalanceRequest updateBalanceRequest) throws MalformedURLException, NoSuchAlgorithmException {
        var amountUpdate = paymentAPIService.updateBalance(updateBalanceRequest.paymentId(), updateBalanceRequest.amountPaid());
        var foundPayment = new PaymentDTO(
                amountUpdate.getPaymentId(), amountUpdate.getOrderId(),
                amountUpdate.getCustomerId(),
                amountUpdate.getAmountDetails(),
                amountUpdate.getPaymentRef(), amountUpdate.getPaymentStatus(),
                amountUpdate.getRemarks(), amountUpdate.getStaffId()
        );
        return Response.accepted(foundPayment).build();
    }

    @POST
    @Path("/find/payment-id")
    public Response doGetByPaymentId(GetPaymentByIdQuery getPaymentByIdQuery) throws MalformedURLException, NoSuchAlgorithmException {
        var paymentByPaymentId = paymentAPIService.getPaymentByPaymentId(getPaymentByIdQuery.paymentId());
        var foundPayment = new PaymentDTO(
                paymentByPaymentId.getPaymentId(), paymentByPaymentId.getOrderId(),
                paymentByPaymentId.getCustomerId(),
                paymentByPaymentId.getAmountDetails(),
                paymentByPaymentId.getPaymentRef(), paymentByPaymentId.getPaymentStatus(),
                paymentByPaymentId.getRemarks(), paymentByPaymentId.getStaffId()
        );
        return Response.ok(foundPayment).build();
    }
}
