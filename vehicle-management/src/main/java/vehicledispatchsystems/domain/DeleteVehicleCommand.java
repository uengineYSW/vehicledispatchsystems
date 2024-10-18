package vehicledispatchsystems.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.Data;

@Data
public class DeleteVehicleCommand {

    private String model;
    private String registrationNumber;
}
