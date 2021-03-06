package dk.notfound.notifier.controller;

import dk.notfound.notifier.model.*;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;


@Slf4j
@RestController
public class WebhookController {

    @Autowired
    EventRepository eventRepository;
    @Autowired
    GroupRepository groupRepository;
    @Autowired
    ServiceEntityRepository serviceEntityRepository;


    @SneakyThrows
    @PostMapping(path="/webhook/{groupName}/{serviceIdentifier}")
    public @ResponseBody
    Event createEvent(@PathVariable String groupName, @PathVariable String serviceIdentifier, @RequestBody String eventRaw) {

       Optional<ServiceEntity> serviceEntityOptional=serviceEntityRepository.findByServiceIdentifier(serviceIdentifier);

       Optional<Group> group = groupRepository.findByGroupName(groupName);
       Event event = new Event(serviceIdentifier,eventRaw);

       if(serviceEntityOptional.isPresent() && serviceEntityOptional.get().getAutoAcknowledgeEventOnReception()==true) {
           event.setAcknowledged(serviceEntityOptional.get().getAutoAcknowledgeEventOnReception());
           event.setEventResponsible("SYSTEM-ACKNOWLEDGED");
       }

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

