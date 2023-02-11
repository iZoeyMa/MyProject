package com.repository;

import com.model.CustomerModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerModel, Long> {

    CustomerModel findById(long id);

    List<CustomerModel> findAll();

    CustomerModel save(CustomerModel transaction);

    void deleteById(long id);
}
