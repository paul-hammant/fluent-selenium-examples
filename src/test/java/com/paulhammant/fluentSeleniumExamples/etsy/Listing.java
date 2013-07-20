package com.paulhammant.fluentSeleniumExamples.etsy;

import com.paulhammant.fluentSeleniumExamples.WholeSuiteListener;
import org.openqa.selenium.WebDriver;
import org.seleniumhq.selenium.fluent.FluentWebDriver;
import org.seleniumhq.selenium.fluent.FluentWebElement;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.openqa.selenium.By.className;

public class Listing extends FluentWebDriver {
    public Listing(WebDriver delegate) {
        super(delegate, WholeSuiteListener.codehaleMetricsMonitor);
        monitor.addClass(this.getClass());
        assertThat(delegate.getCurrentUrl(), containsString("etsy.com/listing/"));
    }

    protected FluentWebElement addToCartButton() {
        return button(className("btn-transaction"));
    }

}
