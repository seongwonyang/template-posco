package stmall.infra;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.naming.NameParser;
import javax.naming.NameParser;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import stmall.config.kafka.KafkaProcessor;
import stmall.domain.*;

//<<< Clean Arch / Inbound Adaptor
@Service
@Transactional
public class PolicyHandler {

    @Autowired
    재고Repository 재고Repository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString) {}

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='배송완료됨'"
    )
    public void whenever배송완료됨_재고차감(@Payload 배송완료됨 배송완료됨) {
        배송완료됨 event = 배송완료됨;
        System.out.println(
            "\n\n##### listener 재고차감 : " + 배송완료됨 + "\n\n"
        );

        // Sample Logic //
        재고.재고차감(event);
    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='배송수거됨'"
    )
    public void whenever배송수거됨_재고증가(@Payload 배송수거됨 배송수거됨) {
        배송수거됨 event = 배송수거됨;
        System.out.println(
            "\n\n##### listener 재고증가 : " + 배송수거됨 + "\n\n"
        );

        // Sample Logic //
        재고.재고증가(event);
    }
}
//>>> Clean Arch / Inbound Adaptor
