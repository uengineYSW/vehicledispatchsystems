package vehicledispatchsystems.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.Data;

@Data
public class DeleteUserCommand {

    private String registrationNumber;
}
