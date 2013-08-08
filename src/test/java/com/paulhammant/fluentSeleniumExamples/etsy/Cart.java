package com.paulhammant.fluentSeleniumExamples.etsy;

import com.paulhammant.fluentSeleniumExamples.WholeSuiteListener;
import org.openqa.selenium.WebDriver;
import org.seleniumhq.selenium.fluent.FluentWebDriver;
import org.seleniumhq.selenium.fluent.TestableString;

import static org.openqa.selenium.By.id;
import static org.seleniumhq.selenium.fluent.Period.secs;

public class Cart extends FluentWebDriver {
    public Cart(WebDriver delegate) {
        super(delegate, WholeSuiteListener.codahaleMetricsMonitor);
        url().within(secs(3)).shouldContain("etsy.com/cart/");
    }

    protected TestableString numberOfItemsInCartHeader() {
        return div(id("checkout-header")).h1().getText();
    }

}
