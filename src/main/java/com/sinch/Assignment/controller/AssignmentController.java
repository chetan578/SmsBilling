package com.sinch.Assignment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sinch.Assignment.entities.Customer;
import com.sinch.Assignment.entities.Sms;
import com.sinch.Assignment.service.AssignmentService;

@RestController
public class AssignmentController {

	@Autowired
	private AssignmentService assignmentService;

	@PostMapping("/saveCustomer")
	public void saveCustomer(@RequestBody Customer customer) {
		assignmentService.saveCustomer(customer);
	}

	@PostMapping("/saveSms")
	public void saveSms(@RequestBody Sms sms) {
		assignmentService.saveSms(sms);
	}

	@GetMapping("/getSmsBillingAmount")
	public double getSmsBillingAmount(@RequestParam int customerId) {
		return assignmentService.getBillingAmount(customerId);
	}

}
