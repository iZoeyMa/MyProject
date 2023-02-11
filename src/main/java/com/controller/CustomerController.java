package com.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exception.ResourceNotFoundException;
import com.model.CustomerModel;
import com.service.CustomerService;

@RestController
@RequestMapping("/api/v1")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @GetMapping("/customers")
    public List<CustomerModel> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @GetMapping("/customers/{id}")
    public ResponseEntity<CustomerModel> getCustomerById(@PathVariable(value = "id") Long customerId)
            throws ResourceNotFoundException {
        CustomerModel customer = customerService.getCustomerById(customerId);
        return ResponseEntity.ok().body(customer);
    }




    @PostMapping("/customers")
    public CustomerModel createCustomer(@Valid @RequestBody CustomerModel customer) {
        return customerService.createCustomer(customer);
    }

    @PutMapping("/customers/{id}")
    public ResponseEntity<CustomerModel> updateCustomer(@PathVariable(value = "id") Long customerId,
                                                   @Valid @RequestBody CustomerModel customerDetails) throws ResourceNotFoundException {
        CustomerModel updatedCustomer = customerService.updateCustomer(customerId, customerDetails);
        return ResponseEntity.ok(updatedCustomer);
    }

    @DeleteMapping("/customers/{id}")
    public void deleteCustomer(@PathVariable(value = "id") Long customerId) throws ResourceNotFoundException {
        customerService.deleteCustomer(customerId);
    }
}
