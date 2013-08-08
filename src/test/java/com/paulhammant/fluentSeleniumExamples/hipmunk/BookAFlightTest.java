package com.paulhammant.fluentSeleniumExamples.hipmunk;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BookAFlightTest {

    private FirefoxDriver wd;

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

        new Home(wd)
                .searchRoute("DFW", "ORD")
                .selectLegs()
                .startBookingViaPriceLineDotCom()
                .assertPriceAsPromised();

    }

}
