package com.example.assignment.repository;

import com.example.assignment.domain.Loan;

import java.util.List;

public interface LoanRepository {

  void createLoan(Loan loan);

  List<Loan> getLoanByCustomer(String customerId);

  Loan getLoanDueDate(String customerId);

  Loan getRecentLoan(String customerId);

  void save(Loan loan);
}
