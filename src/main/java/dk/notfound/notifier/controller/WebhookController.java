package dk.notfound.notifier.controller;


import dk.notfound.notifier.model.Event;
import dk.notfound.notifier.model.EventRepository;
import dk.notfound.notifier.model.Group;
import dk.notfound.notifier.model.GroupRepository;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Slf4j
@RestController
public class WebhookController {

    @Autowired
    EventRepository eventRepository;
    @Autowired
    GroupRepository groupRepository;

    @SneakyThrows
    @PostMapping(path="/webhook/{groupName}/{serviceIdentifier}")
    public @ResponseBody
    Event createEvent(@PathVariable String groupName, @PathVariable String serviceIdentifier, @RequestBody String eventRaw) {

       Optional<Group> group = groupRepository.findByGroupName(groupName);
       Event event = new Event(serviceIdentifier,eventRaw);

       if (group.isPresent()) {
           event.setGroup(group.get());
           eventRepository.save(event);
       }
       else
       {
           log.info("Group doesn't exist- input error");
           throw new ResponseStatusException(HttpStatus.NOT_FOUND);
       }

        return event;
    }


}

