package dk.notfound.notifier.controller;

import dk.notfound.notifier.ApplicationConfiguration;
import dk.notfound.notifier.model.Event;
import dk.notfound.notifier.model.EventRepository;
import dk.notfound.notifier.model.ServiceEntity;
import dk.notfound.notifier.model.ServiceEntityRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Optional;


@Slf4j
@Configuration
@EnableScheduling
public class ServiceEntityScheduler {


 @Autowired
 EventRepository eventRepository;

 @Autowired
 ServiceEntityRepository serviceEntityRepository;

 //Auto acknowledge events when TimeToLive in seconds + CreateTime is smaller than current Time.
 @Scheduled(cron ="*/10 * * * * *")
 public void autoAcknowledgeEvents() {
     Calendar cal = Calendar.getInstance();
     Timestamp currentTime = new Timestamp(cal.getTimeInMillis());

    Iterable<Event> eventList = eventRepository.findAllByAcknowledged(false);
    Optional<ServiceEntity> serviceEntityOptional;

      for(Event e: eventList) {

             serviceEntityOptional = serviceEntityRepository.findByServiceIdentifier(e.getServiceIdentifier());


             if(serviceEntityOptional.isPresent() &&
                     serviceEntityOptional.get().getAutoAcknowledgeEvent()==true
                     && e.getCreated_ts().toInstant().plusSeconds(serviceEntityOptional.get().getEventAcknowledgeTimer()).isBefore( currentTime.toInstant() )  ) {
                 e.setAcknowledged(true);
                 eventRepository.save(e);
                 log.info("Auto acknowledged event: " + e.getId() + " " + e.getServiceIdentifier() +  "after: " + serviceEntityOptional.get().getEventAcknowledgeTimer() );
             }
      }

}


}







