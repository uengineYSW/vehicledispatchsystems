package vehicledispatchsystems.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import vehicledispatchsystems.domain.*;
import vehicledispatchsystems.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class DriverCreated extends AbstractEvent {

    private String name;
    private String affiliation;
    private String contactNumber;
    private Boolean isAvailable;

    public DriverCreated(Driver aggregate) {
        super(aggregate);
    }

    public DriverCreated() {
        super();
    }
}
//>>> DDD / Domain Event
