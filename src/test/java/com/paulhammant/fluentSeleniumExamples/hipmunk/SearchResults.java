package com.paulhammant.fluentSeleniumExamples.hipmunk;

import com.paulhammant.fluentSeleniumExamples.BasePage;
import com.paulhammant.fluentSeleniumExamples.HasATimer;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.seleniumhq.selenium.fluent.FluentExecutionStopped;
import org.seleniumhq.selenium.fluent.FluentWebElement;

import static org.openqa.selenium.By.className;
import static org.openqa.selenium.By.id;
import static org.seleniumhq.selenium.fluent.Period.secs;

public class SearchResults extends BasePage {
    private final String from;
    private final String to;

    public SearchResults(FirefoxDriver delegate, HasATimer bizOperationTiming, String from, String to) {
        super(delegate, bizOperationTiming);
        this.from = from;
        this.to = to;
        within(secs(2)).url().shouldContain("hipmunk.com/flights/");

        // move past interstitial
        within(secs(20)).div(id("sub-graph-1"));
    }

    public BookingOverlay selectLegs() {
        waitForFlightListFor(from + "\n" + to);
        bizOperationTiming.getTimer().end(true);
        timeBizOperation(from + "->" + to + " Return Leg Search Results");
        firstShownLeg().click();
        waitForFlightListFor(from + "\n" + to);
        bizOperationTiming.getTimer().end(true);
        try {
            firstShownLeg().click();
            return new BookingOverlay((FirefoxDriver) delegate, bizOperationTiming);
        } catch (FluentExecutionStopped e) {
            // WebDriver can be too fast for the onClick for the button.
            if (e.getMessage().contains("booking-info-container")) {
                firstShownLeg().click();
                return new BookingOverlay((FirefoxDriver) delegate, bizOperationTiming);
            }
            throw e;
        }
    }

    protected void waitForFlightListFor(final String flights) {
        div(id("leg-selector-1")).div(className("selected")).getText().within(secs(2)).shouldMatch(flights + ".*");
    }

    protected FluentWebElement firstShownLeg() {
        return div(className("select-leg"));
    }

}
