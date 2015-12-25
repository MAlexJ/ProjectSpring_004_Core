package lessons.dao;

import lessons.services.GreetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class AutowiredClass {

    @Autowired
    @Qualifier("greenServiceImpl2")
    private GreetingService greetingService;


    public String getSting(){
      return  greetingService.sayGreeting()+"NNEEEEEEEEEWwwwwwwww ";
    }
}