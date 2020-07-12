package com.billing.setu.controller;
import com.billing.setu.exception.BadRequestException;
import com.billing.setu.exception.EntityNotFoundException;
import com.billing.setu.model.*;
import com.billing.setu.service.BillingAPIRepository;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class BillingController {

    @Autowired
    private BillingAPIRepository repository;

    @ApiOperation(value = "Query your system and get outstanding balance for a customer.", response = BillingResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Respond with the name of the customer, due Date, due amount and a reference ID."),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
            @ApiResponse(code = 500, message = "Internal server error")
        }
    )
    @RequestMapping(
            value = "/api/v1/fetch-bill",
            method = POST,
            headers = "Accept=application/json")
    ResponseEntity<?> fetchBill(@RequestBody Map<String, String> payload) throws Exception {
            String phoneNumber = payload.get("mobileNumber");
            if (phoneNumber == null || phoneNumber.isEmpty()) {
                throw new BadRequestException("");
            }
            BillResponse billResponse = repository.fetch(phoneNumber);
            if (billResponse == null) {
                ErrorResponse errorResponse = new ErrorResponse("ERROR", "customer-not-found");
                throw new EntityNotFoundException("");
            }
            BillingResponseEntity billingResponseEntity = new BillingResponseEntity("SUCCESS", billResponse);
            return new ResponseEntity<>(billingResponseEntity, HttpStatus.OK);
    }

    @ApiOperation(value = "Update the system with a payment.", response = PaymentUpdateResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Respond with acknowledgement ID from the system for payment"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
            @ApiResponse(code = 500, message = "Internal server error")
    }
    )
    @RequestMapping(
            value = "/api/v1/payment-update",
            method = POST,
            headers = "Accept=application/json")
    ResponseEntity<?> paymentUpdate(@RequestBody PaymentUpdateRequest paymentUpdateRequest) throws Exception{
        if(paymentUpdateRequest == null || ObjectUtils.isEmpty(paymentUpdateRequest)) {
            throw new BadRequestException("");
        }
        if(StringUtils.isEmpty(paymentUpdateRequest.getRefID())
                || ObjectUtils.isEmpty(paymentUpdateRequest.getTransaction())) {
            throw new BadRequestException("");
        }
        if(StringUtils.isEmpty(paymentUpdateRequest.getTransaction().getAmountPaid())
                || StringUtils.isEmpty(paymentUpdateRequest.getTransaction().getDate())
                || StringUtils.isEmpty(paymentUpdateRequest.getTransaction().getId())) {
            throw new BadRequestException("");
        }
        PaymentUpdateResponse paymentUpdateResponse =  repository.updatePayment(paymentUpdateRequest);
        return new ResponseEntity<>(paymentUpdateResponse, HttpStatus.OK);
    }
}