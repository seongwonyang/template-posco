package stmall.common;

import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import stmall.주문Application;

@CucumberContextConfiguration
@SpringBootTest(classes = { 주문Application.class })
public class CucumberSpingConfiguration {}
