package stmall.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import stmall.domain.*;
import stmall.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class 주문됨 extends AbstractEvent {

    private Long id;

    public 주문됨(주문 aggregate) {
        super(aggregate);
    }

    public 주문됨() {
        super();
    }
}
//>>> DDD / Domain Event
