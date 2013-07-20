package com.paulhammant.fluentSeleniumExamples.hipmunk;

import com.paulhammant.fluentSeleniumExamples.WholeSuiteListener;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BookAFlightTest {

    private WebDriver wd;

    @Before
    public void makeWebDriverAndGotoSite() {
        wd = new FirefoxDriver();
        wd.get("http://hipmunk.com");
        WholeSuiteListener.codehaleMetricsMonitor.addClass(this.getClass());
    }

    @After
    public void killWebDriver() {
        wd.quit();
    }

    @Test
    public void a_booking_through_to_a_hipmunk_partner() {

        new Home(wd) {{
                fromAirportField().sendKeys("DFW");
                waitForExpandoToComplete();
                toAirportField().sendKeys("ORD");
                searchButton().click();
        }};

        new SearchResults(wd) {{
            waitForFlightListFor("DFW → ORD");
            firstShownLeg().click();
            waitForFlightListFor("ORD → DFW");
            firstShownLeg().click();

            new BookingOverlay(wd) {{
                    firstBookButton().click();
            }};
        }};
    }
}
