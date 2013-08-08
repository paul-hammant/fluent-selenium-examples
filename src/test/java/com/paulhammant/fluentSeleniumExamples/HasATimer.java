package com.paulhammant.fluentSeleniumExamples;

import org.seleniumhq.selenium.fluent.Monitor;

public class HasATimer {

    private Monitor.Timer timer;

    public Monitor.Timer getTimer() {
        return timer;
    }

    public void setTimer(Monitor.Timer timer) {
        this.timer = timer;
    }
}
