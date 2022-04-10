package com.natwesthackerearth.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.natwesthackerearth.model.TransactionRequest;

@Repository
public interface TransactionRepository extends CrudRepository<TransactionRequest, Long> {
	
}
