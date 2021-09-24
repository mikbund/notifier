

package dk.notfound.notifier.model;


import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.IndexColumn;
import org.hibernate.annotations.UpdateTimestamp;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;


@Entity
@Table(name="groups",
      //  uniqueConstraints=@UniqueConstraint(name="UNIQ_GroupName",columnNames={"groupName"}),
        indexes = {
            @Index(name="IDX_group_created_ts",columnList = "created_ts"),
            @Index(name="IDX_group_updated_ts",columnList = "updated_ts"),
            @Index(name="IDX_group_name",columnList = "groupName"),
        }
    )
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    String groupName;

    @Column
    @NotNull
    Boolean enabled;

    @CreationTimestamp
    private Timestamp created_ts;

    @UpdateTimestamp
    private Timestamp updated_ts;

    public Long getId() {
        return id;
    }

    public String getGroupName() {
        return groupName;
    }

    public Boolean getEnabled() { return enabled; }

        //LocalDateTime
    public Timestamp getCreated_ts() {
        return created_ts;
    }

    public Timestamp getUpdated_ts() {
        return updated_ts;
    }


    public String toString() {
        return "id: " + id + " groupName: " + groupName + " enabled: " + enabled;
    }
}
