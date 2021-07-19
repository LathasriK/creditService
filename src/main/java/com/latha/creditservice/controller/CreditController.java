package com.latha.creditservice.controller;

import com.latha.creditservice.dao.CreditRepository;
import com.latha.creditservice.model.CreditRecord;
import com.latha.creditservice.model.CreditRecordResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Random;


@RestController
@RequestMapping("/credit")
public class CreditController {

    private static final int MIN_CREDIT_SCORE = 500;
    private static final int MAX_CREDIT_SCORE = 900;

    @Autowired
    public CreditRepository creditService;

    @PostMapping("/addRecord")
    public CreditRecordResponse addRecord(@RequestBody CreditRecord user) {
        CreditRecordResponse response = new CreditRecordResponse();
        if (user.getPanNumber().isEmpty()) {
            response.setMessage("Cannot add record. Pan number is empty!");
        } else {
            creditService.save(user);
            response.setMessage("Record saved successfully!");
            response.setCreditRecord(user);
        }
        return response;
    }

    @GetMapping("/getAllRecords")
    public List<CreditRecord> getAll() {
        return (List<CreditRecord>) creditService.findAll();
    }


    @GetMapping("/getCreditScore/{panNumber}")
    public int getCreditSore(@PathVariable String panNumber) {
        Optional<CreditRecord> record = creditService.findById(panNumber);
        if (record.isPresent()) {
            return record.get().getCreditScore();
        }
        return 0;
    }


    @GetMapping("/refreshCreditRecord/{panNumber}")
    public Optional<CreditRecord> refreshCreditRecord(@PathVariable String panNumber) {

        Optional<CreditRecord> existingRecord = creditService.findById(panNumber);

        if (existingRecord.isPresent()) {
            CreditRecord record = existingRecord.get();
            Random random = new Random();
            int newScore = random.nextInt(MAX_CREDIT_SCORE);
//            record.setCreditScore(Math.max(newScore, MIN_CREDIT_SCORE));
            record.setCreditScore(newScore);
            creditService.save(record);
            return Optional.of(record);
        }

        return Optional.of(null);
    }

    @GetMapping("/getCreditRecord/{panNumber}")
    public CreditRecordResponse getCreditScore(@PathVariable String panNumber) {

        Optional<CreditRecord> CreditRecord = creditService.findById(panNumber);

        CreditRecordResponse response = new CreditRecordResponse();

        if (panNumber.length() < 6) {
            response.setMessage("Invalid pan Number :" + panNumber + ", Length must be minimum 6");
        } else if (CreditRecord.isPresent()) {
            response.setCreditRecord(CreditRecord.get());
        } else {
            response.setMessage("Credit record not found with pan Number:" + panNumber);
        }
        return response;
    }
}





