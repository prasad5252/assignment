package com.example.assignment.web;


import com.example.assignment.domain.Loan;
import com.example.assignment.exception.LoanCustomizedException;
import com.example.assignment.service.LoanService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/accounts")
@Slf4j
public class LoanController {

    private final LoanService loanService;

    @Autowired
    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> createAccount(@RequestBody Loan account) {
        log.info("Creating loan {}", account);
        try {
            this.loanService.createLoanEntry(account);
        } catch (LoanCustomizedException loanCustomizedException) {
            return new ResponseEntity<>(loanCustomizedException.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(HttpStatus.CREATED);
    }


}
