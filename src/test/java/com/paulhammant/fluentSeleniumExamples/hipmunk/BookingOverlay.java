package com.paulhammant.fluentSeleniumExamples.hipmunk;

import org.openqa.selenium.WebDriver;
import org.seleniumhq.selenium.fluent.FluentWebDriverImpl;
import org.seleniumhq.selenium.fluent.FluentWebElement;

import static org.openqa.selenium.By.className;
import static org.seleniumhq.selenium.fluent.Period.secs;

public class BookingOverlay extends FluentWebDriverImpl {
    public BookingOverlay(WebDriver delegate) {
        super(delegate, Context.singular(null, "SearchResults", ""));

        within(secs(8)).div(className("booking-info-container"));

    }

    protected FluentWebElement firstBookButton() {
        return div(className("booking-link-row")).link();
    }

}
