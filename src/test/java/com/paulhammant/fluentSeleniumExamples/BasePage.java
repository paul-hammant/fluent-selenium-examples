package com.paulhammant.fluentSeleniumExamples;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.seleniumhq.selenium.fluent.FluentWebDriver;
import org.seleniumhq.selenium.fluent.monitors.CompositeMonitor;
import org.seleniumhq.selenium.fluent.monitors.ScreenShotOnError;

public class BasePage extends FluentWebDriver {


    protected final HasATimer bizOperationTiming;

    public BasePage(FirefoxDriver delegate, HasATimer bizOperationTiming) {
        super(delegate, new CompositeMonitor(WholeSuiteListener.codahaleMetricsMonitor,
                new ScreenShotOnError.WithUnitTestFrameWorkContext(delegate, BasePage.class, "test-classes", "surefire-reports")));
        this.bizOperationTiming = bizOperationTiming;
    }

    public void timeBizOperation(String description) {
        bizOperationTiming.setTimer(WholeSuiteListener.codahaleMetricsMonitor.start(description + " (End User Experience)"));
    }


}
