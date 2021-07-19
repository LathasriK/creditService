package com.latha.creditservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreType;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreditRecordResponse {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private CreditRecord creditRecord;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String message;
}
