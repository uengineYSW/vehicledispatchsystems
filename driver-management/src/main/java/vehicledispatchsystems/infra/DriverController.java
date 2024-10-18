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
// @RequestMapping(value="/drivers")
@Transactional
public class DriverController {

    @Autowired
    DriverRepository driverRepository;

    @RequestMapping(
        value = "/drivers/{id}/",
        method = RequestMethod.PUT,
        produces = "application/json;charset=UTF-8"
    )
    public Driver updateDriver(
        @PathVariable(value = "id") String id,
        @RequestBody UpdateDriverCommand updateDriverCommand,
        HttpServletRequest request,
        HttpServletResponse response
    ) throws Exception {
        System.out.println("##### /driver/updateDriver  called #####");
        Optional<Driver> optionalDriver = driverRepository.findById(id);

        optionalDriver.orElseThrow(() -> new Exception("No Entity Found"));
        Driver driver = optionalDriver.get();
        driver.updateDriver(updateDriverCommand);

        driverRepository.save(driver);
        return driver;
    }
}
//>>> Clean Arch / Inbound Adaptor
