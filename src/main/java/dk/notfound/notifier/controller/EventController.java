package dk.notfound.notifier.controller;


import dk.notfound.notifier.model.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Slf4j
@RestController
public class EventController {

    @Autowired
    EventRepository eventRepository;

    @Autowired
    GroupRepository groupRepository;


    @GetMapping(path="/events")
    @ResponseBody public Iterable<Event> listEvents() {
        Iterable<Event> events = eventRepository.findAll();
        return events;
    }


    // for use with versioning
    //@RequestMapping(value = "/user", headers = {"X-API-VERSION=v1"})

    @GetMapping(path="/events/unhandled")
    public @ResponseBody  Iterable<Event> listUnhandledEvents() {
        Iterable<Event> events = eventRepository.findAllByAcknowledged(false);
        return events;
    }


    @GetMapping(path="/events/groups/{groupName}/unhandled")
    public @ResponseBody  Iterable<Event> listUnhandledEvents(@PathVariable String groupName) {

        Optional<Group> group = groupRepository.findByGroupName(groupName);
        Iterable<Event> events;

        if(group.isPresent()) {
            events = eventRepository.FindAllByGroupAndAcknowledged(group.get(), false);
        } else {
            log.info("Group doesn't exist- input error");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return events;
    }


    @GetMapping(path="/events/{id}")
    public @ResponseBody Event listEvents(@PathVariable Long id) {
        Optional<Event> events = eventRepository.findById(id);

        return events.get();
    }

    @PostMapping(path="/events/service/{serviceIdentifier}")
    public @ResponseBody Event createEvent(@PathVariable String serviceIdentifier, @RequestBody String eventRaw) {
      Event event = new Event(serviceIdentifier,eventRaw);
        eventRepository.save(event);
        return event;
    }

    @PutMapping(path="/events/{id}")
    public @ResponseBody Event ackKnowledge(@PathVariable Long id, @RequestBody Event event) {
        Optional<Event> repoEvent = eventRepository.findById(id);
        repoEvent.get().setAcknowledged(event.getAcknowledged());
        repoEvent.get().setEventResponsible(event.getEventResponsible());
        eventRepository.save(repoEvent.get());
        return repoEvent.get();
    }
}

