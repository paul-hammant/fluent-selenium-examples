package com.paulhammant.fluentSeleniumExamples.hipmunk;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.seleniumhq.selenium.fluent.FluentWebElement;
import org.seleniumhq.selenium.fluent.Monitor;

import java.util.Set;

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
            bizOperationTiming = monitor.start("DFW->ORD Initial Search Results (End User Experience)");
            searchButton().click();
        }};

        new SearchResults(wd) {{
            waitForFlightListFor("DFW\nORD");
            bizOperationTiming.end();
            bizOperationTiming = monitor.start("DFW->ORD Return Leg Search Results (End User Experience)");
            firstShownLeg().click();
            waitForFlightListFor("ORD\nDFW");
            bizOperationTiming.end();
            firstShownLeg().click();

            new BookingOverlay(wd) {{
                hipmunkWindowHandle = delegate.getWindowHandle();
                FluentWebElement fluentWebElement = pricelineRow();
                claimedPricelinePrice = Integer.parseInt(fluentWebElement.div().getText().toString().replace("$",""));
                bizOperationTiming = monitor.start("DFW->ORD Priceline Transfer (End User Experience)");
                fluentWebElement.link().click();
                changeWebDriverWindow(delegate);
            }

            };
        }};

        new PricelineAffiliateBooking(wd) {{
            confirmPriceIs(claimedPricelinePrice);
            bizOperationTiming.end();
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

}
