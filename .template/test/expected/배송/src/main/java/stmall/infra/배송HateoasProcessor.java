package stmall.infra;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;
import stmall.domain.*;

@Component
public class 배송HateoasProcessor
    implements RepresentationModelProcessor<EntityModel<배송>> {

    @Override
    public EntityModel<배송> process(EntityModel<배송> model) {
        return model;
    }
}
