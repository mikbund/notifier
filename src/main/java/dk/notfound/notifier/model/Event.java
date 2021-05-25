package dk.notfound.notifier.model;


import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;


import javax.persistence.*;

import javax.validation.constraints.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;


@Entity
@Table(name="events")
public class Event {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;


    @Column
    String serviceIdentifier;

    @Column
    @NotNull
    private Boolean acknowledged;

    @CreationTimestamp
    private Timestamp created_ts;

    @UpdateTimestamp
    private Timestamp updated_ts;

    @Column
    private String eventRaw;


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


    //LocalDateTime
    public Timestamp getCreated_ts() {
        return created_ts;
    }

    public Timestamp getUpdated_ts() {
        return updated_ts;
    }
}
