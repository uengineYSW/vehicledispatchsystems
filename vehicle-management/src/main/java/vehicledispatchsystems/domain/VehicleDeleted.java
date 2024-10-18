package vehicledispatchsystems.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import vehicledispatchsystems.domain.*;
import vehicledispatchsystems.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class VehicleDeleted extends AbstractEvent {

    private String model;
    private String registrationNumber;

    public VehicleDeleted(Vehicle aggregate) {
        super(aggregate);
    }

    public VehicleDeleted() {
        super();
    }
}
//>>> DDD / Domain Event
