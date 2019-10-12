package com.malex.services.impl;

import com.malex.services.Quoter;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Log4j
@Getter
@Setter
public class QuoterImpl implements Quoter {

    private String message;

    @Override
    public void sayQuote() {
        log.debug("message = " + message);
    }
}
