package stmall.common;

import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import stmall.상품Application;

@CucumberContextConfiguration
@SpringBootTest(classes = { 상품Application.class })
public class CucumberSpingConfiguration {}
