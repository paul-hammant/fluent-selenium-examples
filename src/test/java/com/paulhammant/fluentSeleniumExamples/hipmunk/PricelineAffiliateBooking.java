package com.paulhammant.fluentSeleniumExamples.hipmunk;

import com.paulhammant.fluentSeleniumExamples.BasePage;
import com.paulhammant.fluentSeleniumExamples.HasATimer;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.openqa.selenium.By.className;
import static org.openqa.selenium.By.id;
import static org.seleniumhq.selenium.fluent.Period.secs;

public class PricelineAffiliateBooking extends BasePage {
    private final int claimedPricelinePrice;

    public PricelineAffiliateBooking(FirefoxDriver delegate, HasATimer bizOperationTiming, int claimedPricelinePrice) {
        super(delegate, bizOperationTiming);
        this.claimedPricelinePrice = claimedPricelinePrice;
        // move past MANY redirects to the page in question
        within(secs(20)).url().shouldContain("priceline.com/travel/airlines");
        within(secs(15)).h2(className("details_affilate_search_h2"));
    }

    public void assertPriceAsPromised() {
        float actualPrice = getActualPrice(); // includes cents
        assertThat("price", Math.round(actualPrice), equalTo(claimedPricelinePrice));
        bizOperationTiming.getTimer().end(true);
    }

    public float getActualPrice() {
        return Float.parseFloat(span(id("ttp1")).getText().toString().replace("\n", "").trim());
    }


}
