package com.paulhammant.fluentSeleniumExamples.etsy;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class FooTest {

    public static void main(String[] args) {

        WebDriver wd = new FirefoxDriver();
        wd.get("http://jsfiddle.net/SAWsA/11/show/");
        WebElement input = wd.findElement(By.tagName("input"));
        input.sendKeys("hello");
        input = wd.findElement(By.tagName("input"));
        System.out.println("qqq" + input.getAttribute("value"));
        wd.quit();


    }

}
