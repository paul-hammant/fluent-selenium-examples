package com.paulhammant.fluentSeleniumExamples.hipmunk;

import com.paulhammant.fluentSeleniumExamples.BasePage;
import com.paulhammant.fluentSeleniumExamples.HasATimer;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.seleniumhq.selenium.fluent.FluentMatcher;
import org.seleniumhq.selenium.fluent.FluentWebElement;

import java.util.Set;

import static org.openqa.selenium.By.className;
import static org.seleniumhq.selenium.fluent.Period.secs;

public class BookingOverlay extends BasePage {

    private String hipmunkWindowHandle;

    public BookingOverlay(FirefoxDriver delegate, HasATimer bizOperationTiming) {
        super(delegate, bizOperationTiming);
        within(secs(8)).div(className("booking-info-container"));

    }

    public PricelineAffiliateBooking startBookingViaPriceLineDotCom() {
        hipmunkWindowHandle = delegate.getWindowHandle();
        FluentWebElement pricelineRow = pricelineRow();
        int claimedPricelinePrice = Integer.parseInt(pricelineRow.div().getText().toString().replace("$", ""));
        timeBizOperation("DFW->ORD Priceline Transfer");
        pricelineRow.link().click(); // purchase
        changeWebDriverWindow(delegate);
        return new PricelineAffiliateBooking((FirefoxDriver) delegate, bizOperationTiming, claimedPricelinePrice);
    }

    private void changeWebDriverWindow(WebDriver driver) {
        Set<String> handles = driver.getWindowHandles();
        for (String popupHandle : handles) {
            if (!popupHandle.contains(hipmunkWindowHandle)) {
                driver.switchTo().window(popupHandle);
            }
        }
    }
    protected FluentWebElement pricelineRow() {
        return divs(className("booking-link-row")).first(new FluentMatcher() {
            public boolean matches(WebElement webElement) {
                return webElement.getText().contains("Priceline");
            }

            @Override
            public String toString() {
                return "Is PriceLine.com";
            }
        });
    }

}
