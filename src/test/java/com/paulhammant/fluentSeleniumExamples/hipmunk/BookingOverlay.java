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

    public UnitedAirlinesBooking startBookingViaUnitedDotCom() {
        hipmunkWindowHandle = delegate.getWindowHandle();
        FluentWebElement unitedRow = unitedRow();
        int claimedUnitedPrice = Integer.parseInt(unitedRow.div().getText().toString().replace("$", ""));
        timeBizOperation("DFW->ORD United Transfer");
        unitedRow.link().click(); // purchase
        changeWebDriverWindow(delegate);
        return new UnitedAirlinesBooking((FirefoxDriver) delegate, bizOperationTiming, claimedUnitedPrice);
    }

    private void changeWebDriverWindow(WebDriver driver) {
        Set<String> handles = driver.getWindowHandles();
        for (String popupHandle : handles) {
            if (!popupHandle.contains(hipmunkWindowHandle)) {
                driver.switchTo().window(popupHandle);
            }
        }
    }
    protected FluentWebElement unitedRow() {
        return divs(className("booking-link-row")).first(new FluentMatcher() {
            public boolean matches(WebElement webElement) {
                return webElement.getText().contains("United");
            }

            @Override
            public String toString() {
                return "Matches United Airlines";
            }
        });
    }

}
