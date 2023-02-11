package com.service;

import com.exception.CustomerException;
import com.model.CustomerModel;
import com.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public List<CustomerModel> getAllCustomers() {
        return customerRepository.findAll();
    }

    public CustomerModel getCustomerById(Long id) {
        Optional<CustomerModel> customer = customerRepository.findById(id);
        if (!customer.isPresent()) {
            throw new CustomerException("Customer not found", HttpStatus.NOT_FOUND);
        }
        return customer.get();
    }

    public CustomerModel createCustomer(@Valid CustomerModel customer) {
        return customerRepository.save(customer);
    }

    public CustomerModel updateCustomer(Long id, @Valid CustomerModel customer) {
        Optional<CustomerModel> existingCustomer = customerRepository.findById(id);
        if (!existingCustomer.isPresent()) {
            throw new CustomerException("Customer not found", HttpStatus.NOT_FOUND);
        }
        if (!StringUtils.isEmpty(customer.getName())) {
            existingCustomer.get().setName(customer.getName());
        }
        return customerRepository.save(existingCustomer.get());
    }

    public void deleteCustomer(Long id) {
        Optional<CustomerModel> customer = customerRepository.findById(id);
        if (!customer.isPresent()) {
            throw new CustomerException("Customer not found", HttpStatus.NOT_FOUND);
        }
        customerRepository.delete(customer.get());
    }
}


