package dk.notfound.notifier.model;



import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import java.sql.Timestamp;
import java.util.Optional;


public interface GroupRepository extends CrudRepository<Group, Long> {

    //Iterable<Event>  findAllByEnabled(Boolean acknowledged);

    Optional<Group> findById(Long id);
    Optional<Group> findByGroupName(String groupName);


    //@Query(value = "SELECT FROM Group g WHERE g.groupName LIKE '%groupName%'")
    //@Query("select g from Group g where g.groupName = ?1")
  //  Iterable<Group> findByGroupName(String groupName);

}


