package com.paulhammant.fluentSeleniumExamples.hipmunk;

import com.paulhammant.fluentSeleniumExamples.BasePage;
import com.paulhammant.fluentSeleniumExamples.HasATimer;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.openqa.selenium.By.className;
import static org.openqa.selenium.By.id;
import static org.seleniumhq.selenium.fluent.Period.secs;

public class UnitedAirlinesBooking extends BasePage {
    private final int claimedUnitedPrice;

    public UnitedAirlinesBooking(FirefoxDriver delegate, HasATimer bizOperationTiming, int claimedUnitedPrice) {
        super(delegate, bizOperationTiming);
        this.claimedUnitedPrice = claimedUnitedPrice;
        // move past a few redirects to the page in question
        within(secs(20)).url().shouldMatch("united\\.com\\/web\\/.*\\/apps\\/booking");
        within(secs(15)).div(className("priceContinerB"));
    }

    public void assertPriceAsPromised() {
        float actualPrice = getActualPrice(); // includes cents
        assertThat("price", Math.round(actualPrice), equalTo(claimedUnitedPrice));
        bizOperationTiming.getTimer().end(true);
    }

    public float getActualPrice() {
        return Float.parseFloat(td(id("ctl00_ContentInfo_priceRevenueSummary_Price_ctl02_tdDisplayValue")).getText().toString().replace("\n", "").replace("$", "").trim());
    }

}
