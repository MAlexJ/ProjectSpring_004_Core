package com.malex;

import com.malex.services.Quoter;
import com.malex.services.impl.QuoterImpl;
import lombok.extern.log4j.Log4j;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;

@Log4j
public class RunApp {

    public static void main(String[] args) throws Exception {
        log.info("Run App");
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
        while (true) {
            Thread.sleep(1000);
            readAllDefinitionNames(context);
            context.getBean(Quoter.class).sayQuote();
        }
    }

    private static void readAllDefinitionNames(ClassPathXmlApplicationContext context) {
        String[] beanDefinitionNames = context.getBeanDefinitionNames();
        Arrays.stream(beanDefinitionNames).forEach(log::info);
    }
}
