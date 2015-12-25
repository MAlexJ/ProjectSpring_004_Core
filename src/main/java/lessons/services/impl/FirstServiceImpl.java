package lessons.services.impl;

import lessons.services.FirstService;
import org.springframework.stereotype.Component;

@Component
public class FirstServiceImpl implements FirstService {

    @Override
    public String getString() {
        return "First Service";
    }
}
