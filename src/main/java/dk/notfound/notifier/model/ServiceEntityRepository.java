package dk.notfound.notifier.model;


import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import java.sql.Timestamp;
import java.util.Optional;
import java.util.Optional;

public interface ServiceEntityRepository extends CrudRepository<ServiceEntity, Long> {

    Optional<ServiceEntity> findById(Long id);

    Iterable<ServiceEntity>  findAllByAutoAcknowledgeEventOnTimer(Boolean enabled);

    Optional<ServiceEntity> findByServiceIdentifier(String serviceIdentifier);

  //  @Query(value="FROM ServiceEntity s WHERE s.enabled=true AND s.serviceIdentifier=: serviceIdentifier")
  //  Optional<ServiceEntity>  findAllByServiceIdentifierAndEnabled(String serviceIdentifier);




}


