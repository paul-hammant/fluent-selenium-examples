package com.paulhammant.fluentSeleniumExamples.etsy;

import com.paulhammant.fluentSeleniumExamples.BasePage;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.seleniumhq.selenium.fluent.TestableString;

import static org.openqa.selenium.By.id;
import static org.seleniumhq.selenium.fluent.Period.secs;

public class Cart extends BasePage {
    public Cart(FirefoxDriver delegate) {
        super(delegate, null);
        url().within(secs(3)).shouldContain("etsy.com/cart/");
    }

    public void cartShouldContainItems(final int i) {
        // todo - pluralize too.
        numberOfItemsInCartHeader().shouldContain(i + " Item in Your Cart");
    }


    protected TestableString numberOfItemsInCartHeader() {
        return div(id("checkout-header")).h1().getText();
    }

}
