package com.paulhammant.fluentSeleniumExamples.hipmunk;

import com.paulhammant.fluentSeleniumExamples.BasePage;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.openqa.selenium.By.className;
import static org.openqa.selenium.By.id;
import static org.seleniumhq.selenium.fluent.Period.secs;

public class PricelineAffiliateBooking extends BasePage {
    public PricelineAffiliateBooking(FirefoxDriver delegate) {
        super(delegate);
        // move past MANY redirects to the page in question
        within(secs(20)).url().shouldContain("priceline.com/travel/airlines");
        within(secs(15)).h2(className("details_affilate_search_h2"));
    }

    public float getActualPrice() {
        return Float.parseFloat(span(id("ttp1")).getText().toString().replace("\n", "").trim());
    }


}
