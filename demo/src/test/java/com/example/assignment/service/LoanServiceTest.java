package com.example.assignment.service;

import com.example.assignment.domain.Loan;
import com.example.assignment.exception.LoanCustomizedException;
import com.example.assignment.repository.LoanRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class LoanServiceTest {

    @Mock
    private LoanService loanService;

    @Mock
    private LoanRepository loanRepository;


    @Test
    void createLoanSuccess() {
        // Create a sample loan
        Loan loan = new Loan();
        loan.setLoanId("123");
        loan.setCustomerId("456");
        loan.setPaymentDate(LocalDate.of(2023, 9, 10));
        loan.setDueDate(LocalDate.of(2023, 9, 20));
        loan.setAmount(BigDecimal.valueOf(1000.0));

        // Mock behavior for the repository methods
        when(loanRepository.getLoanDueDate("456")).thenReturn(loan); // Assuming this method returns the loan
        when(loanRepository.getRecentLoan("456")).thenReturn(loan); // Assuming this method returns the loan

        // Call the method to be tested
        loanService.createLoanEntry(loan);

        // Verify that the loanRepository.save() method was called with the correct loan object
        verify(loanRepository).save(loan);
    }

    @Test
    void createLoanFailure() {
        // Create a sample loan
        Loan loan = new Loan();
        loan.setLoanId("123");
        loan.setCustomerId("456");
        loan.setPaymentDate(LocalDate.of(2023, 9, 10));
        loan.setDueDate(LocalDate.of(2023, 9, 5)); // Due date is before payment date
        loan.setAmount(BigDecimal.valueOf(1000.0));

        // Mock behavior for the repository methods
        when(loanRepository.getLoanDueDate("456")).thenReturn(loan); // Assuming this method returns the loan

        // Verify that LoanCustomizedException is thrown
        assertThrows(LoanCustomizedException.class, () -> loanService.createLoanEntry(loan));

        // Verify that loanRepository.save() was not called
        verify(loanRepository, never()).save(loan);
    }

}
