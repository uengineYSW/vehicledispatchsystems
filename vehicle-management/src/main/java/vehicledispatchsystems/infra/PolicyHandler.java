package vehicledispatchsystems.infra;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.naming.NameParser;
import javax.naming.NameParser;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import vehicledispatchsystems.config.kafka.KafkaProcessor;
import vehicledispatchsystems.domain.*;

//<<< Clean Arch / Inbound Adaptor
@Service
@Transactional
public class PolicyHandler {

    @Autowired
    VehicleRepository vehicleRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString) {}

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='DriverCreated'"
    )
    public void wheneverDriverCreated_DriverManagementPolicy(
        @Payload DriverCreated driverCreated
    ) {
        DriverCreated event = driverCreated;
        System.out.println(
            "\n\n##### listener DriverManagementPolicy : " +
            driverCreated +
            "\n\n"
        );
        // Sample Logic //

    }
}
//>>> Clean Arch / Inbound Adaptor
