package com.paulhammant.fluentSeleniumExamples.etsy;

import com.paulhammant.fluentSeleniumExamples.BasePage;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.seleniumhq.selenium.fluent.FluentWebElement;

import static org.openqa.selenium.By.id;

public class Home extends BasePage {
    public Home(FirefoxDriver delegate) {
        super(delegate, null);
        url().shouldMatch(".*etsy.com/");
    }

    public SearchResults searchForA(String hat) {
        searchFor().sendKeys(hat);
        searchButton().click();
        return new SearchResults((FirefoxDriver) delegate);
    }

    protected FluentWebElement searchButton() {
        return button(id("search_submit"));
    }

    protected FluentWebElement searchFor() {
        return input(id("search-query"));
    }


}
