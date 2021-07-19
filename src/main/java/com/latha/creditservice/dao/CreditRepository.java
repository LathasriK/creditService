package com.latha.creditservice.dao;

import com.latha.creditservice.model.CreditRecord;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CreditRepository extends CrudRepository<CreditRecord,String> {
    List<CreditRecord> findByPanNumber(String panNumber);



}
