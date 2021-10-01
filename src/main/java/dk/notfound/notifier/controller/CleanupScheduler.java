package dk.notfound.notifier.controller;

import dk.notfound.notifier.ApplicationConfiguration;
import dk.notfound.notifier.model.Event;
import dk.notfound.notifier.model.EventRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Optional;


@Slf4j
@Configuration
@EnableScheduling
public class CleanupScheduler {

    ApplicationConfiguration applicationConfiguration = new ApplicationConfiguration();

    @Autowired
    EventRepository eventRepository;

    @Transactional
    @Scheduled(cron ="0 */30 * * * *")
    public void deleteObsoleteEvents() {

        Integer maxDays = Integer.valueOf(applicationConfiguration.getEventsMaxAge());
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_WEEK, - maxDays);
        Timestamp ts = new Timestamp(cal.getTimeInMillis());

        // deletes events older than maxDays
        eventRepository.deleteEventsByAge(ts);
        log.info("Deleted events older than:" + maxDays + " days");
    }






}
