package com.paulhammant.fluentSeleniumExamples.hipmunk;

import com.paulhammant.fluentSeleniumExamples.BasePage;
import com.paulhammant.fluentSeleniumExamples.HasATimer;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.seleniumhq.selenium.fluent.FluentWebElement;

import static org.openqa.selenium.By.id;
import static org.seleniumhq.selenium.fluent.Period.secs;

public class Home extends BasePage {

    public Home(FirefoxDriver delegate) {
        super(delegate, new HasATimer());
        url().shouldMatch(".*hipmunk.com/");
    }

    public SearchResults searchRoute(String from, String to) {
        fromAirportField().sendKeys(from);
        waitForExpandoToComplete();
        toAirportField().sendKeys(to);
        timeBizOperation(from + "->" + to + " Initial Search Results");
        searchButton().click();
        return new SearchResults((FirefoxDriver) delegate, bizOperationTiming, from, to);
    }

    protected void waitForExpandoToComplete() {
        div(id("flight-search")).getAttribute("class").within(secs(3)).shouldContain("expanded");
    }

    protected FluentWebElement searchButton() {
        return form(id("flight")).button();
    }

    protected FluentWebElement toAirportField() {
        return input(id("fac2flight"));
    }

    protected FluentWebElement fromAirportField() {
        return input(id("fac1flight"));
    }

}
