package dk.notfound.notifier.controller;


import dk.notfound.notifier.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class EventController {

    @Autowired
    EventRepository eventRepository;

/*
 Endpoint is already exposed as part of rest

    @GetMapping(path="/events")
    public Iterable<Event> listEvents() {
        Iterable<Event> events = eventRepository.findAll();
        return events;
    }

*/
    @GetMapping(path="/events/unhandled")
    public Iterable<Event> listUnhandledEvents() {
        Iterable<Event> events = eventRepository.findAllByAcknowledged(false);
        return events;
    }

/*
  Already implements on collection /events as part of rest-hall
    @GetMapping(path="/events/{id}")
    public Event getEvents(@PathVariable Long id) {
        Optional<Event> events = eventRepository.findById(id);
        return events.get();
    }
*/

    @PostMapping(path="/event/service/{serviceIdentifier}")
    public @ResponseBody
    Event createEvent(@PathVariable String serviceIdentifier, @RequestBody String eventRaw) {
      Event event = new Event(serviceIdentifier,eventRaw);
        eventRepository.save(event);
        return event;
    }

    @PostMapping(path="/events/acknowledge/{id}")
    public @ResponseBody Event ackKnowledge(@PathVariable Long id, @RequestBody Event event) {
        Optional<Event> repoEvent = eventRepository.findById(id);
        repoEvent.get().setAcknowledged(event.getAcknowledged());
        eventRepository.save(repoEvent.get());

        return repoEvent.get();
    }
}

