package vehicledispatchsystems.infra;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;
import vehicledispatchsystems.domain.*;

@Component
public class VehicleHateoasProcessor
    implements RepresentationModelProcessor<EntityModel<Vehicle>> {

    @Override
    public EntityModel<Vehicle> process(EntityModel<Vehicle> model) {
        model.add(
            Link.of(model.getRequiredLink("self").getHref() + "/").withRel("update")
        );
        model.add(
            Link
                .of(
                    model.getRequiredLink("self").getHref() +
                    "/reportvehiclebreakdown"
                )
                .withRel("reportvehiclebreakdown")
        );

        return model;
    }
}
