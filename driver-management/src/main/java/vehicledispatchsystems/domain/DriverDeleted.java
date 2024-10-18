package vehicledispatchsystems.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import vehicledispatchsystems.domain.*;
import vehicledispatchsystems.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class DriverDeleted extends AbstractEvent {

    private String name;

    public DriverDeleted(Driver aggregate) {
        super(aggregate);
    }

    public DriverDeleted() {
        super();
    }
}
//>>> DDD / Domain Event
