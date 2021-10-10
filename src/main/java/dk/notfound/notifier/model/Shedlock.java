package dk.notfound.notifier.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Entity
@Table(name="shedlock")
public class Shedlock {

 @Id
 @Column(name="name", columnDefinition="varchar(64)")
 @NotNull
 String name;

 @Column(name="lock_until", columnDefinition="Timestamp")
 @NotNull
 Timestamp lockUntil;

 @Column(name="locked_at", columnDefinition="Timestamp")
 @NotNull
 Timestamp lockedAt;


 @Column(name="locked_by", columnDefinition="varchar(255)")
 @NotNull
 String lockedBy;


}
