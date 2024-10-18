package vehicledispatchsystems.domain;

import java.util.*;
import lombok.*;
import vehicledispatchsystems.domain.*;
import vehicledispatchsystems.infra.AbstractEvent;

@Data
@ToString
public class UserCreated extends AbstractEvent {

    private String name;
    private String registrationNumber;
    private String dateOfBirth;
    private String address;
}
