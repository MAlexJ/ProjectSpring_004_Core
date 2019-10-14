package com.malex;

import com.malex.services.impl.QuoterImpl;
import lombok.extern.log4j.Log4j;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@Log4j
public class RunApp {

    public static void main(String[] args) {
        log.info("Run App");
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
        context.getBean(QuoterImpl.class).sayQuote();
    }
}
