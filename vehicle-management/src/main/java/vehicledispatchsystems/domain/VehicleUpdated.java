package vehicledispatchsystems.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import vehicledispatchsystems.domain.*;
import vehicledispatchsystems.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class VehicleUpdated extends AbstractEvent {

    private String manufacturer;
    private String model;
    private String usage;
    private String company;
    private String registrationNumber;

    public VehicleUpdated(Vehicle aggregate) {
        super(aggregate);
    }

    public VehicleUpdated() {
        super();
    }
}
//>>> DDD / Domain Event
