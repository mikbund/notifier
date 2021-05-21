package dk.notfound.notifier.model;


import org.springframework.data.repository.CrudRepository;

import java.util.Optional;


public interface EventRepository extends CrudRepository<Event, Long> {


    Iterable<Event>  findAllByAcknowledged(Boolean acknowledged);

}
