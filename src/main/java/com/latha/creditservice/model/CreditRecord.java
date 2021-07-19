package com.latha.creditservice.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonInclude;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class CreditRecord {
    @Id
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String panNumber;
    private String userName;
    private int creditScore;
    private int yearOfBirth;
}
