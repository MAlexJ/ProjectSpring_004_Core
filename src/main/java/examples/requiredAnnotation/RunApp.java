package examples.requiredAnnotation;

import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@Log4j
public class RunApp {

    public static void main(String[] args) {
        try {
            log.info("Run app: " + RunApp.class.getSimpleName());
            ClassPathXmlApplicationContext context
                    = new ClassPathXmlApplicationContext("required-context.xml");

            PrintService printService = context.getBean(PrintService.class);
            printService.print();
        } catch (BeanCreationException ex) {
            log.error(ex);
        }
    }
}
