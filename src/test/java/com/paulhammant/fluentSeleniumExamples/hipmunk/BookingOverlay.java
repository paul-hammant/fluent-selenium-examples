package com.paulhammant.fluentSeleniumExamples.hipmunk;

import com.paulhammant.fluentSeleniumExamples.WholeSuiteListener;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.seleniumhq.selenium.fluent.FluentMatcher;
import org.seleniumhq.selenium.fluent.FluentWebDriver;
import org.seleniumhq.selenium.fluent.FluentWebElement;

import static org.openqa.selenium.By.className;
import static org.seleniumhq.selenium.fluent.Period.secs;

public class BookingOverlay extends FluentWebDriver {
    public BookingOverlay(WebDriver delegate) {
        super(delegate, WholeSuiteListener.codahaleMetricsMonitor);
        within(secs(8)).div(className("booking-info-container"));

    }

    protected FluentWebElement pricelineRow() {
        return divs(className("booking-link-row")).first(new FluentMatcher() {
            public boolean matches(WebElement webElement) {
                return webElement.getText().contains("Priceline");
            }

            @Override
            public String toString() {
                return "Is PriceLine.com";
            }
        });
    }

}
