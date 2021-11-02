package dk.notfound.notifier.model;



import org.hibernate.annotations.ColumnTransformer;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.IndexColumn;
import org.hibernate.annotations.UpdateTimestamp;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Table(name="events", indexes = {
        @Index(name="IDX_acknowledged",columnList = "acknowledged"),
        @Index(name="IDX_created_ts",columnList = "created_ts"),
        @Index(name="IDX_updated_ts",columnList = "updated_ts"),
        @Index(name="IDX_serviceIdentifier",columnList = "serviceIdentifier"),
        @Index(name="IDX_eventResponsible",columnList = "eventResponsible"),
}   )
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    @Column
    String serviceIdentifier;

    @Column
    String eventResponsible;

    @Column
    @NotNull
    private Boolean acknowledged;

    @OneToOne
    //@ManyToOne
    //@JoinColumn(name="fk_group_id", referencedColumnName = "id")
    private Group group;

    @CreationTimestamp
    private Timestamp created_ts;

    @UpdateTimestamp
    private Timestamp updated_ts;

    @Column(columnDefinition="TEXT")
    private String eventRaw;

    public void setEventResponsible(String eventResponsible) {
        this.eventResponsible = eventResponsible;
    }

    public String getEventResponsible() {
        return eventResponsible;
    }

    public Event() {
        this.acknowledged=false;
    }

    public Event(String serviceIdentifier, String eventRaw) {
        this.acknowledged=false;
        this.serviceIdentifier = serviceIdentifier;
        this.eventRaw=eventRaw;
    }

    public Long getId() {
        return Id;
    }

    public Boolean getAcknowledged() {
        return acknowledged;
    }

    public void setAcknowledged(Boolean acknowledged) {
        this.acknowledged = acknowledged;
    }

    public String getEventRaw() {
        return eventRaw;
    }

    public void setEventRaw(String eventRaw) {
        this.eventRaw = eventRaw;
    }

    public String getServiceIdentifier() {
        return serviceIdentifier;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group=group;
    }

    //LocalDateTime
    public Timestamp getCreated_ts() {
        return created_ts;
    }

    public Timestamp getUpdated_ts() {
        return updated_ts;
    }
}
