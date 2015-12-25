package lessons.controller;

import lessons.config.AppConfiguration;
import lessons.services.FirstService;
import lessons.services.GreetingService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class MainController {
    private static final Logger logger = LogManager.getLogger(MainController.class);

    public static void main(String[] args) {
        logger.info(">>>>   Starting configuration...");
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguration.class);

        GreetingService greetingService = context.getBean(GreetingService.class);
        logger.info(">>>>   " +greetingService.sayGreeting());  // "Greeting, user!"

        FirstService firstService = context.getBean(FirstService.class);
        logger.info(">>>>   " + firstService.getString());
    }
}
