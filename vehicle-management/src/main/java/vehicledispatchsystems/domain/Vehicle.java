package vehicledispatchsystems.domain;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;
import vehicledispatchsystems.VehicleManagementApplication;
import vehicledispatchsystems.domain.VehicleCreated;
import vehicledispatchsystems.domain.VehicleDeleted;

@Entity
@Table(name = "Vehicle_table")
@Data
//<<< DDD / Aggregate Root
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String manufacturer;

    private String model;

    private String usage;

    private String company;

    private String registrationNumber;

    private Boolean inUse;

    @PostPersist
    public void onPostPersist() {
        VehicleCreated vehicleCreated = new VehicleCreated(this);
        vehicleCreated.publishAfterCommit();

        VehicleDeleted vehicleDeleted = new VehicleDeleted(this);
        vehicleDeleted.publishAfterCommit();
    }

    @PrePersist
    public void onPrePersist() {}

    public static VehicleRepository repository() {
        VehicleRepository vehicleRepository = VehicleManagementApplication.applicationContext.getBean(
            VehicleRepository.class
        );
        return vehicleRepository;
    }

    //<<< Clean Arch / Port Method
    public void updateVehicle(UpdateVehicleCommand updateVehicleCommand) {
        //implement business logic here:

        VehicleUpdated vehicleUpdated = new VehicleUpdated(this);
        vehicleUpdated.publishAfterCommit();
    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public void reportVehicleBreakdown(
        ReportVehicleBreakdownCommand reportVehicleBreakdownCommand
    ) {
        //implement business logic here:

        VehicleBreakdownReported vehicleBreakdownReported = new VehicleBreakdownReported(
            this
        );
        vehicleBreakdownReported.publishAfterCommit();
    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
