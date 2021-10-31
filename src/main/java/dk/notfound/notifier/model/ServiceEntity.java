package dk.notfound.notifier.model;


import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;


@Entity
@Table(name="service_entities", indexes = {
        @Index(name="IDX_serviceIdentifier",columnList = "serviceIdentifier"),
        @Index(name="IDX_AutoAcknowledgeEventOnReception",columnList = "AutoAcknowledgeEventOnReception"),
        @Index(name="IDX_autoAcknowledgeEventOnTimer",columnList = "autoAcknowledgeEventOnTimer")
}   )

public class ServiceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, unique = true)
    String serviceIdentifier;

    @Column
    @NotNull
    private Long eventAcknowledgeTimer;

    @Column
    @NotNull
    private Boolean autoAcknowledgeEventOnReception = false;


    @Column
    Timestamp autoAcknowledgeEventOnReceptionUntilTs;


    @Column
    @NotNull
    private Boolean autoAcknowledgeEventOnTimer = false;

    @CreationTimestamp
    private Timestamp created_ts;

    @UpdateTimestamp
    private Timestamp updated_ts;


    public Long getId() {
        return id;
    }

    public void setEventAcknowledgeTimer(Long eventAcknowledgeTimer) {
        this.eventAcknowledgeTimer = eventAcknowledgeTimer;
    }

    public Long getEventAcknowledgeTimer() {
        return eventAcknowledgeTimer;
    }


    public String getServiceIdentifier() {
        return serviceIdentifier;
    }

    public void setAutoAcknowledgeEventOnTimer(Boolean autoAcknowledgeEventOnTimer) {
        this.autoAcknowledgeEventOnTimer = autoAcknowledgeEventOnTimer;
    }

    public Boolean getAutoAcknowledgeEventOnTimer() {
        return autoAcknowledgeEventOnTimer;
    }


    public Boolean getAutoAcknowledgeEventOnReception() {
        return autoAcknowledgeEventOnReception;
    }

    public void setAutoAcknowledgeEventOnReception(Boolean autoAcknowledgeEventOnReception) {
        this.autoAcknowledgeEventOnReception = autoAcknowledgeEventOnReception;
    }

    public Timestamp getAutoAcknowledgeEventOnReceptionUntilTs() {
        return autoAcknowledgeEventOnReceptionUntilTs;
    }

    public void setAutoAcknowledgeEventOnReceptionUntilTs(Timestamp autoAcknowledgeEventOnReceptionUntilTs) {
        this.autoAcknowledgeEventOnReceptionUntilTs = autoAcknowledgeEventOnReceptionUntilTs;
    }




}
