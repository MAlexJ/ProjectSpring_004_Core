package com.malex.controller.impl;

import com.malex.controller.ProfilerControllerMXBean;

public class ProfilerController implements ProfilerControllerMXBean {

    private boolean enabled;

    @Override
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
