package com.example.assignment.service;

import com.example.assignment.domain.Loan;
import com.example.assignment.exception.LoanCustomizedException;
import com.example.assignment.repository.LoanRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@Slf4j
public class LoanService {

    @Autowired
    private LoanRepository loanRepository;

    public void createLoanEntry(Loan loan) {
        log.info("Inside loan service to register new loan entry: {}", loan.getLoanId());

        //validating for payment date condition
        if(loan.getPaymentDate().isAfter(loan.getDueDate())) {
            throw new LoanCustomizedException("Rejecting Loan: Payment date can't be greater than due date");
        }
        //if loan crosses due date
        Loan checkDueDateLoan = loanRepository.getLoanDueDate(loan.getCustomerId());
        if(LocalDate.now().isAfter(checkDueDateLoan.getDueDate())) {
            //writing alert log
            log.error("Loan crossed the due date");
        }
        Loan getRecentLoanEntry = loanRepository.getRecentLoan(loan.getCustomerId());

        //set remaining amount
        loan.setRemainingAmount(getRecentLoanEntry.getRemainingAmount().subtract(loan.getAmount()));

        loanRepository.save(loan);
    }
}
