package com.malex.controller.impl;

import com.malex.controller.ProfilerControllerMBean;
import lombok.Getter;

@Getter
public class ProfilerController implements ProfilerControllerMBean {

    private boolean enabled;

    @Override
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
