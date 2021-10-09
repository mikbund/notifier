package dk.notfound.notifier.controller;

import dk.notfound.notifier.model.Event;
import dk.notfound.notifier.model.EventRepository;
import dk.notfound.notifier.model.ServiceEntity;
import dk.notfound.notifier.model.ServiceEntityRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.sql.Timestamp;
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
 public void autoAcknowledgeEventsOnTimer() {
     Calendar cal = Calendar.getInstance();
     Timestamp currentTime = new Timestamp(cal.getTimeInMillis());

    Iterable<Event> eventList = eventRepository.findAllByAcknowledged(false);
    Optional<ServiceEntity> serviceEntityOptional;

      for(Event e: eventList) {

             serviceEntityOptional = serviceEntityRepository.findByServiceIdentifier(e.getServiceIdentifier());

             if(serviceEntityOptional.isPresent() &&
                     serviceEntityOptional.get().getAutoAcknowledgeEventOnTimer()==true
                     && e.getCreated_ts().toInstant().plusSeconds(serviceEntityOptional.get().getEventAcknowledgeTimer()).isBefore( currentTime.toInstant() )  ) {
                 e.setAcknowledged(true);
                 e.setEventResponsible("SYSTEM-SCHEDULER");
                 eventRepository.save(e);
                 log.info("Auto acknowledged event: " + e.getId() + " " + e.getServiceIdentifier() +  "after: " + serviceEntityOptional.get().getEventAcknowledgeTimer() );
             }
      }
 }

 /*

    events will on time passed change AutoAcknowledeEventReception.
  */
@Scheduled(cron = "*/15 * * * * *")
public void disableAutoAcknowledgeEventsOnReception() {
    Calendar cal = Calendar.getInstance();

     Iterable<ServiceEntity> serviceEntities;
     serviceEntities = serviceEntityRepository.findAllByAutoAcknowledgeEventOnReception(true);

     Timestamp currentTimeStamp = new Timestamp(cal.getTimeInMillis());

     for(ServiceEntity s: serviceEntities) {

            if (s.getAutoAcknowledgeEventOnReceptionUntilTs()!=null && s.getAutoAcknowledgeEventOnReceptionUntilTs().before( currentTimeStamp )) {
                s.setAutoAcknowledgeEventOnReception(false);
                serviceEntityRepository.save(s);
                log.info("Disabled AutoAcknowledgeEventOnReception for: " + s.getServiceIdentifier());
            }
     }



}


}







