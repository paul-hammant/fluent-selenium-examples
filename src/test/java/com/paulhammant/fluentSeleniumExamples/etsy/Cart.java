package com.paulhammant.fluentSeleniumExamples.etsy;

import org.openqa.selenium.WebDriver;
import org.seleniumhq.selenium.fluent.FluentWebDriver;
import org.seleniumhq.selenium.fluent.TestableString;

import static org.openqa.selenium.By.id;
import static org.seleniumhq.selenium.fluent.Period.secs;

public class Cart extends FluentWebDriver {
    public Cart(WebDriver delegate) {
        super(delegate);

        url().within(secs(1)).shouldContain("etsy.com/cart/");
    }

    protected TestableString numberOfItemsInCartHeader() {
        return div(id("checkout-header")).h1().getText();
    }

}
