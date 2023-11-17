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
    배송Repository 배송Repository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString) {}

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='주문됨'"
    )
    public void whenever주문됨_배송시작(@Payload 주문됨 주문됨) {
        주문됨 event = 주문됨;
        System.out.println("\n\n##### listener 배송시작 : " + 주문됨 + "\n\n");

        // Sample Logic //
        배송.배송시작(event);
    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='주문취소됨'"
    )
    public void whenever주문취소됨_배송취소(@Payload 주문취소됨 주문취소됨) {
        주문취소됨 event = 주문취소됨;
        System.out.println(
            "\n\n##### listener 배송취소 : " + 주문취소됨 + "\n\n"
        );

        // Sample Logic //
        배송.배송취소(event);
    }
}
//>>> Clean Arch / Inbound Adaptor
