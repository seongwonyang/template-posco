package stmall.common;

import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import stmall.배송Application;

@CucumberContextConfiguration
@SpringBootTest(classes = { 배송Application.class })
public class CucumberSpingConfiguration {}
