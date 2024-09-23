package com.assignment.group.customerservice.service;

import com.assignment.group.customerservice.entity.Customer;
import com.assignment.group.customerservice.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    @Autowired
    private final CustomerRepository customerRepository;
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }
    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }
    public Customer getCustomerById(Long id) {
        return customerRepository.findById(id).get();

    }
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }
    public void deleteCustomer(Long id){
         customerRepository.deleteById(id);
    }
}
