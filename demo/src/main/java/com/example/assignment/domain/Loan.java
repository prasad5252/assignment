package com.example.assignment.domain;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@ToString
public class Loan {

    private String loanId;

    private String CustomerId;

    private String lenderId;

    private BigDecimal amount;

    private BigDecimal remainingAmount;

    private LocalDate paymentDate;

    private Float interestPerDay;

    private LocalDate dueDate;

    private Float penaltyPerDay;

    private  Boolean cancel;

}
