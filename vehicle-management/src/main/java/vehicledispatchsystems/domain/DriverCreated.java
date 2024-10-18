package vehicledispatchsystems.domain;

import java.util.*;
import lombok.*;
import vehicledispatchsystems.domain.*;
import vehicledispatchsystems.infra.AbstractEvent;

@Data
@ToString
public class DriverCreated extends AbstractEvent {

    private String name;
    private String affiliation;
    private String contactNumber;
    private Boolean isAvailable;
}
