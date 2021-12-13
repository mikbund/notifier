package dk.notfound.notifier.model;



import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import java.sql.Timestamp;
import java.util.Optional;


public interface GroupRepository extends CrudRepository<Group, Long> {


    Optional<Group> findById(Long id);
    Optional<Group> findByGroupName(String groupName);



}


