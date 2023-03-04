package com.sinch.Assignment.service;

import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sinch.Assignment.common.PLAN_TYPE;
import com.sinch.Assignment.entities.Customer;
import com.sinch.Assignment.entities.Sms;
import com.sinch.Assignment.repository.CustomerRepo;
import com.sinch.Assignment.repository.SmsRepo;

@Service
public class AssignmentService {

	@Autowired
	private CustomerRepo customerRepo;
	@Autowired
	private SmsRepo smsRepo;

	public void saveCustomer(Customer customer) {
		customerRepo.save(customer);

	}

	public void saveSms(Sms sms) {
		sms.setSentDate(new Date());
		smsRepo.save(sms);

	}

	public double getBillingAmount(int customerId) {
		LocalDateTime date = LocalDateTime.now();
		int month = date.getMonthValue();
		int year = date.getYear();
		Customer customer = customerRepo.findById(customerId).orElse(null);
		int noOfSms = smsRepo.getSmsBillingAmount(customerId, month, year);
		int chargeableSms = 0;
		double effectiveAmount = 0d;
		if (customer.getPlanType().equals(PLAN_TYPE.BASIC)) {
			chargeableSms = noOfSms;
			effectiveAmount = chargeableSms * PLAN_TYPE.BASIC.getPlanAmount();
		} else if (customer.getPlanType().equals(PLAN_TYPE.SILVER)) {
			if (noOfSms > 100)
				chargeableSms = noOfSms - 100;
			effectiveAmount = chargeableSms * PLAN_TYPE.SILVER.getPlanAmount();
		} else {
			int discountedSms = 0;
			if (noOfSms > 100_000) {
				discountedSms = noOfSms - 100_000;
				effectiveAmount = (100_000 - 1000) * PLAN_TYPE.GOLD.getPlanAmount() + discountedSms * 0.0005;
			} else if (noOfSms > 1000) {
				chargeableSms = noOfSms - 1000;
				effectiveAmount = chargeableSms * PLAN_TYPE.GOLD.getPlanAmount();
			}
		}
		return effectiveAmount;

	}

}
