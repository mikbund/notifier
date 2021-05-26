package dk.notfound.notifier.model;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

//CrudRepository
public interface EventRepository extends JpaRepository<Event, Long> {


    Iterable<Event>  findAllByAcknowledged(Boolean acknowledged);

}
