package stmall.domain;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;
import stmall.domain.배송수거됨;
import stmall.domain.배송시작됨;
import stmall.domain.배송완료됨;
import stmall.domain.배송취소됨;
import stmall.배송Application;

@Entity
@Table(name = "배송_table")
@Data
//<<< DDD / Aggregate Root
public class 배송 {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @PostPersist
    public void onPostPersist() {
        배송시작됨 배송시작됨 = new 배송시작됨(this);
        배송시작됨.publishAfterCommit();

        배송완료됨 배송완료됨 = new 배송완료됨(this);
        배송완료됨.publishAfterCommit();

        배송취소됨 배송취소됨 = new 배송취소됨(this);
        배송취소됨.publishAfterCommit();

        배송수거됨 배송수거됨 = new 배송수거됨(this);
        배송수거됨.publishAfterCommit();
    }

    public static 배송Repository repository() {
        배송Repository 배송Repository = 배송Application.applicationContext.getBean(
            배송Repository.class
        );
        return 배송Repository;
    }

    //<<< Clean Arch / Port Method
    public static void 배송시작(주문됨 주문됨) {
        //implement business logic here:

        /** Example 1:  new item 
        배송 배송 = new 배송();
        repository().save(배송);

        */

        /** Example 2:  finding and process
        
        repository().findById(주문됨.get???()).ifPresent(배송->{
            
            배송 // do something
            repository().save(배송);


         });
        */

    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public static void 배송취소(주문취소됨 주문취소됨) {
        //implement business logic here:

        /** Example 1:  new item 
        배송 배송 = new 배송();
        repository().save(배송);

        */

        /** Example 2:  finding and process
        
        repository().findById(주문취소됨.get???()).ifPresent(배송->{
            
            배송 // do something
            repository().save(배송);


         });
        */

    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
