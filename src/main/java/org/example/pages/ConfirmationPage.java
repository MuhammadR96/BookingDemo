package org.example.pages;

import org.openqa.selenium.WebDriver;

public class ConfirmationPage {


    //Constructor
    public ConfirmationPage(WebDriver driver) {
        this.driver = driver;
    }


    WebDriver driver;

    //Actions
    public String getTitle() {
        return driver.getTitle();
    }
}
