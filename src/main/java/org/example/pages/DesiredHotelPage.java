package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class DesiredHotelPage {

    //Constructor
    public DesiredHotelPage(WebDriver driver) {
        this.driver = driver;
    }


    WebDriver driver;


    //Locators
    By reserveButtonLocator = By.xpath("//button[@id='hp_book_now_button']");
    By largeBedRadioButtonLocator = By.xpath("//input[@name='bedPreference_78883120' and @value='2']");
    By amountDropDownMenuLocator = By.xpath("//select[@id='hprt_nos_select_78883120_91939502_0_33_0_41999']");
    By confirmReservationButtonLocator = By.xpath("//button/span[contains(text(), 'reserve')]");


    //Actions
    public WebElement getReserveButton(){
        return driver.findElement(reserveButtonLocator);
    }

    public void clickOnReserveButton(){
        getReserveButton().click();
    }

    public WebElement getLargeBedRadioButton(){
        return driver.findElement(largeBedRadioButtonLocator);
    }

    public void clickOnLargeBedRadioButton(){
        //Using JavascriptExecutor here because Actions.click() & .click() didn't work properly
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("arguments[0].click();", getLargeBedRadioButton());
    }

    public WebElement getAmountDropDownMenu(){
        return driver.findElement(amountDropDownMenuLocator);
    }

    public void selectFromAmountDropDownMenu(){
        Select amountDropDownMenu = new Select(getAmountDropDownMenu());
        amountDropDownMenu.selectByValue("1");
    }

    public WebElement getConfirmReservationButton(){
        return driver.findElement(confirmReservationButtonLocator);
    }

    public void clickOnConfirmReservationButton(){
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("arguments[0].click();", getConfirmReservationButton());
    }

}
