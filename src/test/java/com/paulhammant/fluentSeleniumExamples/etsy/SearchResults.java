package com.paulhammant.fluentSeleniumExamples.etsy;

import com.paulhammant.fluentSeleniumExamples.WholeSuiteListener;
import org.openqa.selenium.WebDriver;
import org.seleniumhq.selenium.fluent.FluentWebDriver;
import org.seleniumhq.selenium.fluent.FluentWebElement;
import org.seleniumhq.selenium.fluent.TestableString;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.openqa.selenium.By.className;
import static org.openqa.selenium.By.id;

public class SearchResults extends FluentWebDriver {
    public SearchResults(WebDriver delegate) {
        super(delegate, WholeSuiteListener.codehaleMetricsMonitor);
        monitor.addClass(this.getClass());
        assertThat(delegate.getCurrentUrl(), containsString("/search?"));
    }

    protected FluentWebElement firstNonSponsoredListing() {
        return ul(className("listings")).lis().get(0);
    }

    protected TestableString numberMatchingSearchHeader() {
        return div(id("search-header")).h1().getText();
    }

}
