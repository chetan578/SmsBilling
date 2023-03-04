package com.sinch.Assignment.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sinch.Assignment.entities.Sms;

@Repository
public interface SmsRepo extends CrudRepository<Sms, Integer> {

	@Query("select count(id) from Sms where customerId =:customerId and YEAR(sentDate)=:year and MONTH(sentDate)=:month")
	int getSmsBillingAmount(int customerId, int month, int year);

}
