package org.example.pages;

import org.example.utilities.DateFormatter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ConfirmationPage {


    //Constructor
    public ConfirmationPage(WebDriver driver) {
        this.driver = driver;
    }


    WebDriver driver;


    //Locators
    By dateRangeLocator = By.xpath("//div[@class='bui-date-range bui-date-range--large bp-date-range']");
    By checkInDateLocator = By.xpath("//div[@class='bui-date-range bui-date-range--large bp-date-range']/div[1]/time/span[1]");
    By checkOutDateLocator = By.xpath("//div[@class='bui-date-range bui-date-range--large bp-date-range']/div[2]/time/span[1]");
    By hotelDataCardLocator = By.xpath("//div[@class='bp-mfe-container--property-details']");



    //Actions
    public String getTitle() {
        return driver.getTitle();
    }

    public String getDateRange() {
        return driver.findElement(dateRangeLocator).getText();
    }

    private String getRawCheckInDate(){
        return driver.findElement(checkInDateLocator).getText();
    }

    private String getRawCheckOutDate(){
        return driver.findElement(checkOutDateLocator).getText();
    }

    public String getCheckInDate(){
        String rawCheckInDate = getRawCheckInDate();
//        System.out.println(rawCheckInDate);       //Checkpoint (Done)
        DateFormatter dateFormatter = new DateFormatter();
        String checkInDate = dateFormatter.changeConfirmationPageDatesFormat(rawCheckInDate);
        return checkInDate;
    }

    public String getCheckOutDate(){
        String rawCheckOutDate = getRawCheckOutDate();
        DateFormatter dateFormatter = new DateFormatter();
        String checkOutDate = dateFormatter.changeConfirmationPageDatesFormat(rawCheckOutDate);
        return checkOutDate;
    }

    public WebElement getHotelDataCard () {
        return driver.findElement(hotelDataCardLocator);
    }
}
