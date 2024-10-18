package vehicledispatchsystems.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import vehicledispatchsystems.domain.*;
import vehicledispatchsystems.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class VehicleBreakdownReported extends AbstractEvent {

    private String model;
    private String registrationNumber;
    private Boolean inUse;

    public VehicleBreakdownReported(Vehicle aggregate) {
        super(aggregate);
    }

    public VehicleBreakdownReported() {
        super();
    }
}
//>>> DDD / Domain Event
