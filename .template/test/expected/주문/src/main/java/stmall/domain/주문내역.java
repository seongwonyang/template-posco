package stmall.domain;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;

//<<< EDA / CQRS
@Entity
@Table(name = "주문내역_table")
@Data
public class 주문내역 {

    @Id
    //@GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
}
