package com.billing.setu.service;

import com.billing.setu.model.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, BigInteger> {

}
