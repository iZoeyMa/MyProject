package com.repository;

import com.model.TransactionModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionModel, Long> {

//    TransactionModel findById(long id);
//    List<TransactionModel> findById(Long id);
    List<TransactionModel> findByCustomerId(Long customerId);
    TransactionModel findById(long id);



    List<TransactionModel> findAll();

    TransactionModel save(TransactionModel transaction);

    void deleteById(long id);
}
