package stmall.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import stmall.domain.*;
import stmall.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class 배송완료됨 extends AbstractEvent {

    private Long id;

    public 배송완료됨(배송 aggregate) {
        super(aggregate);
    }

    public 배송완료됨() {
        super();
    }
}
//>>> DDD / Domain Event
