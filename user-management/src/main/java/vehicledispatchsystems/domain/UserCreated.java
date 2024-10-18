package vehicledispatchsystems.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import vehicledispatchsystems.domain.*;
import vehicledispatchsystems.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class UserCreated extends AbstractEvent {

    private String name;
    private String registrationNumber;
    private String dateOfBirth;
    private String address;

    public UserCreated(User aggregate) {
        super(aggregate);
    }

    public UserCreated() {
        super();
    }
}
//>>> DDD / Domain Event
