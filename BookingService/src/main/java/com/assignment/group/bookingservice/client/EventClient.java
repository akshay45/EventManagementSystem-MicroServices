package com.assignment.group.bookingservice.client;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "EventService")
public interface EventClient {

    @GetMapping("/api/events/{id}")
    String getEventById(@PathVariable("id") Long id);
}

