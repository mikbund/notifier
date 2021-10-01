package dk.notfound.notifier.model;


import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;


@Entity
@Table
public class ServiceEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable = false, unique = true)
    String serviceIdentifier;

    @Column
    @NotNull
    private Long eventAcknowledgeTimer;

    @Column
    @NotNull
    private Boolean autoAcknowledgeEvent = false;

    @CreationTimestamp
    private Timestamp created_ts;

    @UpdateTimestamp
    private Timestamp updated_ts;

    public void setEventAcknowledgeTimer(Long eventAcknowledgeTimer) {
        this.eventAcknowledgeTimer = eventAcknowledgeTimer;
    }

    public Long getEventAcknowledgeTimer() {
        return eventAcknowledgeTimer;
    }


    public String getServiceIdentifier() {
        return serviceIdentifier;
    }


    public Long getId() {
        return id;
    }

    public void setAutoAcknowledgeEvent(Boolean autoAcknowledgeEvent) {
        this.autoAcknowledgeEvent = autoAcknowledgeEvent;
    }

    public Boolean getAutoAcknowledgeEvent() {
        return autoAcknowledgeEvent;
    }








}
