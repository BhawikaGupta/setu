package com.billing.setu.service;

import com.billing.setu.exception.AmountMisMatchException;
import com.billing.setu.exception.EntityNotFoundException;
import com.billing.setu.exception.InvalidRefIdException;
import com.billing.setu.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigInteger;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;



@Service
public class BillingAPIRepository {

    @Autowired
    private CustomerRepository customerRepository;

    public BillResponse fetch(String mobileNumber) {
        BigInteger number = new BigInteger(mobileNumber);
        Optional<Customer> response = customerRepository.findById(number);
        if (response.isPresent()) {
            Customer customer = response.get();
            BillResponse billResponse = new BillResponse();
            billResponse.setCustomerName(customer.getCustomerName());
            billResponse.setDueAmount(customer.getDueAmount());
            billResponse.setDueDate(customer.getDueDate());
            billResponse.setRefID(customer.getRefID());
            return billResponse;
        }
        throw new EntityNotFoundException("Customer not found");
    }

    public PaymentUpdateResponse updatePayment(PaymentUpdateRequest paymentUpdateRequest) {
        Iterable<Customer> response = customerRepository.findAll();
        for (Customer customer: response) {
            if (customer.getRefID().equals(paymentUpdateRequest.getRefID())) {
                if(!StringUtils.isEmpty(customer.getId())
                        && (!customer.getId().equals(paymentUpdateRequest.getTransaction().getId()))) {
                    throw new InvalidRefIdException("Reference id doesn't match");
                }

                if(Objects.equals(customer.getDueAmount(), "0")) {
                    if (! customer.getAmountPaid().equals(paymentUpdateRequest.getTransaction().getAmountPaid())) {
                        throw new AmountMisMatchException("Amount Mismatch");
                    }
                    PaymentResponse paymentResponse = new PaymentResponse(customer.getAckID());
                    return new PaymentUpdateResponse("SUCCESS", paymentResponse);
                }

                if(!customer.getDueAmount().equals(paymentUpdateRequest.getTransaction().getAmountPaid())
                        || ( ! StringUtils.isEmpty(customer.getAmountPaid()) &&
                                !customer.getAmountPaid().equals(paymentUpdateRequest.getTransaction().getAmountPaid()))) {
                    throw new AmountMisMatchException("Amount Mismatch");
                }
                customer.setAmountPaid(paymentUpdateRequest.getTransaction().getAmountPaid());
                customer.setDate(paymentUpdateRequest.getTransaction().getDate());
                customer.setId(paymentUpdateRequest.getTransaction().getId());
                customer.setDueAmount("0");
                customer.setAckID(String.valueOf(UUID.randomUUID()));
                customerRepository.save(customer);

                PaymentResponse paymentResponse = new PaymentResponse(customer.getAckID());
                return new PaymentUpdateResponse("SUCCESS", paymentResponse);
            }
        }
        throw new InvalidRefIdException("Ref id not found");
    }

}
