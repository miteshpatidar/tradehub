package com.mitesh.portfolio_service.repository;

import com.mitesh.portfolio_service.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction,Integer> {
}
