package com.billing.setu;

import com.billing.setu.model.PaymentUpdateRequest;
import com.billing.setu.model.Transaction;
import com.billing.setu.service.BillingAPIRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import static com.billing.setu.configuration.ApplicationConfiguration.dropExisitingTables;

@SpringBootApplication
public class SetuBillingApiApplication {

	public static void main(String[] args) {
		dropExisitingTables();
		ConfigurableApplicationContext ctx =
				SpringApplication.run(SetuBillingApiApplication.class, args);

		/*Transaction transaction = new Transaction("2000", "2020-06-05", "OUAB012344");
		PaymentUpdateRequest paymentUpdateResponse = new PaymentUpdateRequest("AX0812878", transaction);
		BillingAPIRepository billingAPIRepository = ctx.getBean(BillingAPIRepository.class);
		billingAPIRepository.updatePayment(paymentUpdateResponse);*/


	}

}
