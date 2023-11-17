package stmall.infra;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import stmall.config.kafka.KafkaProcessor;
import stmall.domain.*;

@Service
public class 상품조회ViewHandler {

    //<<< DDD / CQRS
    @Autowired
    private 상품조회Repository 상품조회Repository;
    //>>> DDD / CQRS
}
