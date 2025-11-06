package com.mitesh.portfolio_service.repository;

import com.mitesh.portfolio_service.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account,Integer> {
}
