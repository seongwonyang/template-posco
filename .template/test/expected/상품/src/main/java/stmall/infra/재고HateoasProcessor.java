package stmall.infra;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;
import stmall.domain.*;

@Component
public class 재고HateoasProcessor
    implements RepresentationModelProcessor<EntityModel<재고>> {

    @Override
    public EntityModel<재고> process(EntityModel<재고> model) {
        return model;
    }
}
