package vehicledispatchsystems.infra;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;
import vehicledispatchsystems.domain.*;

@Component
public class DriverHateoasProcessor
    implements RepresentationModelProcessor<EntityModel<Driver>> {

    @Override
    public EntityModel<Driver> process(EntityModel<Driver> model) {
        model.add(
            Link.of(model.getRequiredLink("self").getHref() + "/").withRel("")
        );

        return model;
    }
}
