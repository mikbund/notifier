package dk.notfound.notifier.controller;


import dk.notfound.notifier.model.Event;
import dk.notfound.notifier.model.EventRepository;
import dk.notfound.notifier.model.Group;
import dk.notfound.notifier.model.GroupRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Optional;

@Slf4j
@RestController
public class WebhookController {

    @Autowired
    EventRepository eventRepository;
    @Autowired
    GroupRepository groupRepository;

    @PostMapping(path="/webhook/{groupName}/{serviceIdentifier}")
    public @ResponseBody
    Event createEvent(@PathVariable String groupName, @PathVariable String serviceIdentifier, @RequestBody String eventRaw) {


        Optional<Group> group;
        Event event = new Event(serviceIdentifier,eventRaw);
         group = groupRepository.findByGroupName(groupName);


        event.setGroup(group.get());


       if (!group.isPresent())
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Group doesn't exist - create group");

        eventRepository.save(event);
        return event;
    }


}

