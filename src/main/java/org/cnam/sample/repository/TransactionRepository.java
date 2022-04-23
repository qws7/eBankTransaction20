package org.cnam.sample.repository;

import org.cnam.sample.model.TransactionModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TransactionRepository extends JpaRepository<TransactionModel, Long> {

}
