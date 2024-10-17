package stmall.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import stmall.domain.*;
import stmall.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class 재고차감됨 extends AbstractEvent {

    private Long id;

    public 재고차감됨(재고 aggregate) {
        super(aggregate);
    }

    public 재고차감됨() {
        super();
    }
}
//>>> DDD / Domain Event
