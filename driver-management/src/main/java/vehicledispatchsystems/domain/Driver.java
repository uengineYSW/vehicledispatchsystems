package vehicledispatchsystems.domain;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;
import vehicledispatchsystems.DriverManagementApplication;
import vehicledispatchsystems.domain.DriverCreated;
import vehicledispatchsystems.domain.DriverDeleted;

@Entity
@Table(name = "Driver_table")
@Data
//<<< DDD / Aggregate Root
public class Driver {

    @Id
    private String name;

    private String affiliation;

    private String contactNumber;

    private Boolean isAvailable;

    @PostPersist
    public void onPostPersist() {
        DriverCreated driverCreated = new DriverCreated(this);
        driverCreated.publishAfterCommit();

        DriverDeleted driverDeleted = new DriverDeleted(this);
        driverDeleted.publishAfterCommit();
    }

    @PrePersist
    public void onPrePersist() {}

    public static DriverRepository repository() {
        DriverRepository driverRepository = DriverManagementApplication.applicationContext.getBean(
            DriverRepository.class
        );
        return driverRepository;
    }

    //<<< Clean Arch / Port Method
    public void updateDriver(UpdateDriverCommand updateDriverCommand) {
        //implement business logic here:

        DriverUpdated driverUpdated = new DriverUpdated(this);
        driverUpdated.publishAfterCommit();
    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
