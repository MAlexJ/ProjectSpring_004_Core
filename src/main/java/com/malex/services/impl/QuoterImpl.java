package com.malex.services.impl;

import com.malex.annotations.InjectRandomInt;
import com.malex.services.Quoter;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j;

import javax.annotation.PostConstruct;
import java.util.stream.IntStream;

@Log4j
@Getter
@Setter
public class QuoterImpl implements Quoter {

    @InjectRandomInt(min = 1, max = 7)
    private int repeat;

    private String message;

    public QuoterImpl() {
        log.info("Java Constructor | repeat = " + repeat);
    }

    @PostConstruct
    public void init(){
        log.info("PostConstruct | repeat = " + repeat);
    }

    @Override
    public void sayQuote() {
        IntStream.range(0, repeat)
                .forEach(num -> log.info("message = " + message));
    }
}
