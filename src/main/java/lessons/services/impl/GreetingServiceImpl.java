package lessons.services.impl;

import lessons.services.GreetingService;

public class GreetingServiceImpl implements GreetingService {

    @Override
    public String sayGreeting() {
        return "Greeting, user!";
    }
}