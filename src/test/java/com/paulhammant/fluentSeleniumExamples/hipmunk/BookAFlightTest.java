package com.paulhammant.fluentSeleniumExamples.hipmunk;

import com.paulhammant.fluentSeleniumExamples.WholeSuiteListener;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.seleniumhq.selenium.fluent.FluentWebElement;
import org.seleniumhq.selenium.fluent.Monitor;

import java.util.Set;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class BookAFlightTest {

    private WebDriver wd;
    private Monitor.Timer bizOperationTiming;
    private int claimedPricelinePrice;
    private String hipmunkWindowHandle;


    @Before
    public void makeWebDriverAndGotoSite() {
        wd = new FirefoxDriver();
        wd.get("http://hipmunk.com");
    }

    @After
    public void killWebDriver() {
        wd.quit();
    }

    @Test
    public void a_booking_through_to_priceline_affiliate() {

        new Home(wd) {{
            fromAirportField().sendKeys("DFW");
            waitForExpandoToComplete();
            toAirportField().sendKeys("ORD");
            timeBizOperation("DFW->ORD Initial Search Results");
            searchButton().click();
        }};

        new SearchResults(wd) {{
            waitForFlightListFor("DFW\nORD");
            bizOperationTiming.end(true);
            timeBizOperation("DFW->ORD Return Leg Search Results");
            firstShownLeg().click();
            waitForFlightListFor("ORD\nDFW");
            bizOperationTiming.end(true);
            firstShownLeg().click();

            new BookingOverlay(wd) {{
                hipmunkWindowHandle = delegate.getWindowHandle();
                FluentWebElement pricelineRow = pricelineRow();
                claimedPricelinePrice = Integer.parseInt(pricelineRow.div().getText().toString().replace("$", ""));
                timeBizOperation("DFW->ORD Priceline Transfer");
                pricelineRow.link().click(); // purchase
                changeWebDriverWindow(delegate);
            }};
        }};

        new PricelineAffiliateBooking(wd) {{
            float actualPrice = getActualPrice(); // includes cents
            assertThat("price", Math.round(actualPrice), equalTo(claimedPricelinePrice));
            bizOperationTiming.end(true);
        }};

    }

    private void changeWebDriverWindow(WebDriver driver) {
        Set<String> handles = driver.getWindowHandles();
        for (String popupHandle : handles) {
            if (!popupHandle.contains(hipmunkWindowHandle)) {
                driver.switchTo().window(popupHandle);
            }
        }
    }

    private void timeBizOperation(String description) {
        bizOperationTiming = WholeSuiteListener.codahaleMetricsMonitor.start(description + " (End User Experience)");
    }

}
