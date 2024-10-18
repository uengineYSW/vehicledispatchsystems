package vehicledispatchsystems;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.concurrent.TimeUnit;
import javax.inject.Inject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.verifier.messaging.MessageVerifier;
import org.springframework.cloud.contract.verifier.messaging.boot.AutoConfigureMessageVerifier;
import org.springframework.cloud.contract.verifier.messaging.internal.ContractVerifierMessage;
import org.springframework.cloud.contract.verifier.messaging.internal.ContractVerifierMessaging;
import org.springframework.cloud.contract.verifier.messaging.internal.ContractVerifierObjectMapper;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.cloud.stream.test.binder.MessageCollector;
import org.springframework.context.ApplicationContext;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.MimeTypeUtils;
import vehicledispatchsystems.config.kafka.KafkaProcessor;
import vehicledispatchsystems.domain.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMessageVerifier
public class ReportVehicleBreakdownTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(
        ReportVehicleBreakdownTest.class
    );

    @Autowired
    private KafkaProcessor processor;

    @Autowired
    private MessageCollector messageCollector;

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    private MessageVerifier<Message<?>> messageVerifier;

    @Autowired
    public VehicleRepository repository;

    @Test
    @SuppressWarnings("unchecked")
    public void test0() {
        //given:
        Vehicle existingEntity = new Vehicle();

        existingEntity.setManufacturer("현대");
        existingEntity.setModel("소나타");
        existingEntity.setUsage("승용차");
        existingEntity.setCompany("ABC주식회사");
        existingEntity.setRegistrationNumber("12가1234");
        existingEntity.setInUse(true);

        repository.save(existingEntity);

        //when:

        try {
            Vehicle newEntity = new Vehicle();

            newEntity.setModel("소나타");
            newEntity.setRegistrationNumber("12가1234");
            newEntity.setInUse(false);

            repository.save(newEntity);

            this.messageVerifier.send(
                    MessageBuilder
                        .withPayload(newEntity)
                        .setHeader(
                            MessageHeaders.CONTENT_TYPE,
                            MimeTypeUtils.APPLICATION_JSON
                        )
                        .build(),
                    "vehicledispatchsystems"
                );

            Message<?> receivedMessage =
                this.messageVerifier.receive(
                        "vehicledispatchsystems",
                        5000,
                        TimeUnit.MILLISECONDS
                    );
            assertNotNull("Resulted event must be published", receivedMessage);

            //then:
            String receivedPayload = (String) receivedMessage.getPayload();

            VehicleBreakdownReported outputEvent = objectMapper.readValue(
                receivedPayload,
                VehicleBreakdownReported.class
            );

            LOGGER.info("Response received: {}", outputEvent);

            assertEquals(outputEvent.getModel(), "소나타");
            assertEquals(outputEvent.getRegistrationNumber(), "12가1234");
            assertEquals(outputEvent.getInUse(), false);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            assertTrue(e.getMessage(), false);
        }
    }
}
