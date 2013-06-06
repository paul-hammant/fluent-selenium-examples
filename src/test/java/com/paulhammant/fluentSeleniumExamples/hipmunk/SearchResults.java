package com.paulhammant.fluentSeleniumExamples.hipmunk;

import org.openqa.selenium.WebDriver;
import org.seleniumhq.selenium.fluent.FluentWebDriver;
import org.seleniumhq.selenium.fluent.FluentWebElement;
import org.seleniumhq.selenium.fluent.RetryAfterStaleElement;

import static org.openqa.selenium.By.className;
import static org.openqa.selenium.By.id;
import static org.seleniumhq.selenium.fluent.Period.secs;

public class SearchResults extends FluentWebDriver {
    public SearchResults(WebDriver delegate) {
        super(delegate);

        within(secs(2)).url().shouldContain("hipmunk.com/flights/");

        // move past interstitial
        within(secs(20)).div(id("sub-graph-1"));



    }

    protected void waitForFlightListFor(final String flights) {
        new RetryAfterStaleElement() {
            public void toRetry() {
                div(className("fromto-column")).getText().within(secs(2)).shouldBe(flights);
            }
        }.stopAfter(secs(8));
    }

    protected FluentWebElement firstShownLeg() {
        return div(id("sub-graph-1")).div(className("select-leg"));
    }


}
