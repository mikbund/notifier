package dk.notfound.notifier.model;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.sql.Timestamp;
//CrudRepository
public interface EventRepository extends CrudRepository<Event, Long> {

    Iterable<Event>  findAllByAcknowledged(Boolean acknowledged);

    @Query(value = "FROM Event e WHERE e.acknowledged= :acknowledged AND e.group= :group")
    Iterable<Event> FindAllByGroupAndAcknowledged(Group group,Boolean acknowledged);

    @Modifying
    @Query(value = "DELETE FROM Event e WHERE e.created_ts< :created")
    void deleteEventsByAge(@Param("created") Timestamp created);


}
