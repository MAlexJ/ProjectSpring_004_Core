package lessons.services.impl;

import lessons.services.GreetingService;
import org.springframework.stereotype.Component;

@Component
public class GreenServiceImpl2 implements GreetingService {

    @Override
    public String sayGreeting() {
        return "YYYYYYYYYYYY";
    }
}
