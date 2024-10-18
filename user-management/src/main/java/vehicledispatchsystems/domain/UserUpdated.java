package vehicledispatchsystems.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import vehicledispatchsystems.domain.*;
import vehicledispatchsystems.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class UserUpdated extends AbstractEvent {

    private String name;
    private String registrationNumber;
    private String dateOfBirth;
    private String address;

    public UserUpdated(User aggregate) {
        super(aggregate);
    }

    public UserUpdated() {
        super();
    }
}
//>>> DDD / Domain Event
