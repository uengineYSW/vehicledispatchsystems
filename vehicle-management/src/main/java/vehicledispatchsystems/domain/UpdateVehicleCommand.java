package vehicledispatchsystems.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.Data;

@Data
public class UpdateVehicleCommand {

    private String manufacturer;
    private String model;
    private String usage;
    private String company;
    private String registrationNumber;
}
