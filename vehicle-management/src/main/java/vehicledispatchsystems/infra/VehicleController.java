package vehicledispatchsystems.infra;

import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vehicledispatchsystems.domain.*;

//<<< Clean Arch / Inbound Adaptor

@RestController
// @RequestMapping(value="/vehicles")
@Transactional
public class VehicleController {

    @Autowired
    VehicleRepository vehicleRepository;

    @RequestMapping(
        value = "/vehicles/{id}/",
        method = RequestMethod.PUT,
        produces = "application/json;charset=UTF-8"
    )
    public Vehicle updateVehicle(
        @PathVariable(value = "id") String id,
        @RequestBody UpdateVehicleCommand updateVehicleCommand,
        HttpServletRequest request,
        HttpServletResponse response
    ) throws Exception {
        System.out.println("##### /vehicle/updateVehicle  called #####");
        Optional<Vehicle> optionalVehicle = vehicleRepository.findById(id);

        optionalVehicle.orElseThrow(() -> new Exception("No Entity Found"));
        Vehicle vehicle = optionalVehicle.get();
        vehicle.updateVehicle(updateVehicleCommand);

        vehicleRepository.save(vehicle);
        return vehicle;
    }

    @RequestMapping(
        value = "/vehicles/{id}/reportvehiclebreakdown",
        method = RequestMethod.PUT,
        produces = "application/json;charset=UTF-8"
    )
    public Vehicle reportVehicleBreakdown(
        @PathVariable(value = "id") String id,
        @RequestBody ReportVehicleBreakdownCommand reportVehicleBreakdownCommand,
        HttpServletRequest request,
        HttpServletResponse response
    ) throws Exception {
        System.out.println(
            "##### /vehicle/reportVehicleBreakdown  called #####"
        );
        Optional<Vehicle> optionalVehicle = vehicleRepository.findById(id);

        optionalVehicle.orElseThrow(() -> new Exception("No Entity Found"));
        Vehicle vehicle = optionalVehicle.get();
        vehicle.reportVehicleBreakdown(reportVehicleBreakdownCommand);

        vehicleRepository.save(vehicle);
        return vehicle;
    }
}
//>>> Clean Arch / Inbound Adaptor
