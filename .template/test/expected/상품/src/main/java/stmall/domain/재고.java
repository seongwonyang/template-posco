package stmall.domain;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;
import stmall.domain.재고증가됨;
import stmall.domain.재고차감됨;
import stmall.상품Application;

@Entity
@Table(name = "재고_table")
@Data
//<<< DDD / Aggregate Root
public class 재고 {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @PostPersist
    public void onPostPersist() {
        재고차감됨 재고차감됨 = new 재고차감됨(this);
        재고차감됨.publishAfterCommit();

        재고증가됨 재고증가됨 = new 재고증가됨(this);
        재고증가됨.publishAfterCommit();
    }

    public static 재고Repository repository() {
        재고Repository 재고Repository = 상품Application.applicationContext.getBean(
            재고Repository.class
        );
        return 재고Repository;
    }

    //<<< Clean Arch / Port Method
    public static void 재고차감(배송완료됨 배송완료됨) {
        //implement business logic here:

        /** Example 1:  new item 
        재고 재고 = new 재고();
        repository().save(재고);

        */

        /** Example 2:  finding and process
        
        repository().findById(배송완료됨.get???()).ifPresent(재고->{
            
            재고 // do something
            repository().save(재고);


         });
        */

    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public static void 재고증가(배송수거됨 배송수거됨) {
        //implement business logic here:

        /** Example 1:  new item 
        재고 재고 = new 재고();
        repository().save(재고);

        */

        /** Example 2:  finding and process
        
        repository().findById(배송수거됨.get???()).ifPresent(재고->{
            
            재고 // do something
            repository().save(재고);


         });
        */

    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
