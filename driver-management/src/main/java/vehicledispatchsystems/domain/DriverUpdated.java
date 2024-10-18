package vehicledispatchsystems.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import vehicledispatchsystems.domain.*;
import vehicledispatchsystems.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class DriverUpdated extends AbstractEvent {

    private String name;
    private String affiliation;
    private String contactNumber;
    private Boolean isAvailable;

    public DriverUpdated(Driver aggregate) {
        super(aggregate);
    }

    public DriverUpdated() {
        super();
    }
}
//>>> DDD / Domain Event
