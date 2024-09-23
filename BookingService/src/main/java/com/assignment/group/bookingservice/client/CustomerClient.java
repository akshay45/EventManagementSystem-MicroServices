package com.assignment.group.bookingservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "CustomerService")
public interface CustomerClient {
    @GetMapping("/api/customer/{id}")
    String getCustomerById(@PathVariable Long id);
}
