package vehicledispatchsystems.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.Data;

@Data
public class CreateDriverCommand {

    private String name;
    private String affiliation;
    private String contactNumber;
    private Boolean isAvailable;
}
