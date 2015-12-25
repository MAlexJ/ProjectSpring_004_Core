package lessons.config;

import lessons.services.impl.FirstServiceImpl;
import lessons.services.impl.GreetingServiceImpl;
import lessons.services.FirstService;
import lessons.services.GreetingService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;
import org.springframework.stereotype.Component;

/**
 * Конфигурационный класс Spring IoC контейнера
 */

@Component
@Configuration
@ComponentScan(basePackages = "lessons")
@Description("Класс конфигурации приложения")
public class AppConfiguration {

    @Bean
    GreetingService greetingService() {
        return new GreetingServiceImpl();
    }

    @Bean
    FirstService service() {
        return new FirstServiceImpl();
    }


}
