package lessons.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;


/**
 * Конфигурационный класс Spring IoC контейнера
 */

@Configuration
@ComponentScan(basePackages = "lessons")
@Description("Класс конфигурации приложения")
public class AppConfiguration {

}
