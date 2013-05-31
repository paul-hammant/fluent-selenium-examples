package com.paulhammant.fluentSeleniumExamples.etsy;

import org.openqa.selenium.WebDriver;
import org.seleniumhq.selenium.fluent.FluentWebDriver;
import org.seleniumhq.selenium.fluent.TestableString;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.openqa.selenium.By.id;

public class Cart extends FluentWebDriver {
    public Cart(WebDriver delegate) {
        super(delegate, Context.singular(null, "Cart", ""));

        assertThat(delegate.getCurrentUrl(), containsString("etsy.com/cart/"));
    }

    protected TestableString numberOfItemsInCartHeader() {
        return div(id("checkout-header")).h1().getText();
    }

}
