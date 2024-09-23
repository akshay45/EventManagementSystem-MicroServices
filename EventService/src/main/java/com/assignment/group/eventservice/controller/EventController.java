package com.assignment.group.eventservice.controller;

import com.assignment.group.eventservice.entity.Event;
import com.assignment.group.eventservice.service.EventServices;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/event")
@Slf4j
public class EventController {
    Logger logger = LoggerFactory.getLogger(EventController.class);

    @Autowired
    private final EventServices eventServices;
    public EventController(EventServices eventServices) {
        this.eventServices = eventServices;
    }
    @GetMapping
    public List<Event> getEvents() {
        return eventServices.getAllEvents();
    }
    @PostMapping
    public Event addEvent(@RequestBody Event event) {
        logger.info("Adding event: {}", event.getEventName());
        return eventServices.addEvent(event);

    }
    @PutMapping
    public Event updateEvent(@RequestBody Event event) {
        logger.info("Updating event: {}", event.getEventName());
        return eventServices.updateEvent(event);
    }
    @DeleteMapping("/{id}")
    public void deleteEvent(@PathVariable Long id) {
        logger.info("Deleting event: {}", id);
        eventServices.deleteEvent(id);
    }


}
