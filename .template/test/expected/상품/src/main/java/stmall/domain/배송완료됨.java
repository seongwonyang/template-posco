package stmall.domain;

import java.util.*;
import lombok.*;
import stmall.domain.*;
import stmall.infra.AbstractEvent;

@Data
@ToString
public class 배송완료됨 extends AbstractEvent {

    private Long id;
}
