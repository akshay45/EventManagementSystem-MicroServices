package com.assignment.group.customerservice.controller;

import com.assignment.group.customerservice.entity.Customer;
import com.assignment.group.customerservice.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {
    @Autowired
    private final CustomerService customerService;
    public CustomerController(CustomerService customerService){
        this.customerService=customerService;
    }
    @GetMapping
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }
    @PostMapping
    public Customer createCustomer(@RequestBody Customer customer) {
        return customerService.createCustomer(customer);
    }
    @DeleteMapping
    public void deleteCustomer(@RequestBody Customer customer) {
        customerService.deleteCustomer(customer.getId());
    }
}
