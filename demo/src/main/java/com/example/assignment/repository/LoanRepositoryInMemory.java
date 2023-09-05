package com.example.assignment.repository;

import com.example.assignment.domain.Loan;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class LoanRepositoryInMemory implements LoanRepository {

    private final List<Loan> loans = new ArrayList<>();




    @Override
    public void createLoan(Loan loan) {
        loans.add(loan);
    }


    public List<Loan> getLoanByCustomer(String customerId) {
        return loans.stream().filter(each -> each.getCustomerId().equals(customerId))
            .collect(Collectors.toList());
    }

    @Override
    public Loan getLoanDueDate(String customerId) {
        List<Loan> collect =
            loans.stream().filter(each -> each.getCustomerId().equals(customerId)).collect(Collectors.toList());
        Comparator<Loan> dueDateComparator = Comparator.comparing(Loan::getDueDate);
        Collections.sort(collect, dueDateComparator);
        return collect.get(0);
    }

    @Override
    public Loan getRecentLoan(String customerId) {
        List<Loan> collect =
            loans.stream().filter(each -> each.getCustomerId().equals(customerId)).collect(Collectors.toList());
        Comparator<Loan> dueDateComparator = Comparator.comparing(Loan::getPaymentDate);
        Collections.sort(collect, dueDateComparator);
        Collections.sort(collect, Collections.reverseOrder());
        return collect.get(0);
    }

    @Override
    public void save(Loan loan) {
        loans.add(loan);
    }
}
