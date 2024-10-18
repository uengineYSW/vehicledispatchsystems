package vehicledispatchsystems.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.Data;

@Data
public class UpdateUserCommand {

    private String name;
    private String registrationNumber;
    private String dateOfBirth;
    private String address;
}
