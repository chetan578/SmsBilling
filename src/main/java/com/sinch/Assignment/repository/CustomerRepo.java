package com.sinch.Assignment.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sinch.Assignment.entities.Customer;

@Repository
public interface CustomerRepo extends CrudRepository<Customer, Integer> {

}
