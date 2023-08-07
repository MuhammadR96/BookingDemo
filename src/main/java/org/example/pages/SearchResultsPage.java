package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.io.IOException;
import java.util.List;

public class SearchResultsPage {

    //Constructor
    public SearchResultsPage(WebDriver driver) throws IOException {
        this.driver = driver;
    }


    WebDriver driver;


    public String desiredHotel = "Tolip Hotel Alexandria";


    //Locators
    private By desiredHotelLocator = By.xpath("//div[contains(text(),'" + desiredHotel + "')]");
    private By nextPageButtonLocator = By.xpath("//button[@aria-label=\"Next page\"]");



    //Actions
    public WebElement getDesiredHotelElement(){
        WebElement desiredHotelElement = driver.findElement(desiredHotelLocator);
        return desiredHotelElement;
    }

    public void clickOnDesiredHotel(){
        Actions actions = new Actions(driver);
        WebElement desiredHotelElement = getDesiredHotelElement();
        if (desiredHotelElement.isDisplayed()) {
            actions.scrollToElement(desiredHotelElement);
            actions.click(desiredHotelElement).build().perform();
        }
        //Switching to the new tab after clicking on the hotel
        driver.getWindowHandles().forEach(tab->driver.switchTo().window(tab));
    }

    public void clickOnNextPageButton(){
        Actions actions = new Actions(driver);
        WebElement nextPageButtonElement = driver.findElement(nextPageButtonLocator);
        actions.scrollToElement(nextPageButtonElement);
        actions.click(nextPageButtonElement).build().perform();
    }

    public void findDesiredHotel() throws InterruptedException {
        List<WebElement> hotels;
        String desiredHotel = "";

        while (!desiredHotel.contains("Tolip")){
            Thread.sleep(1500);
            hotels = driver.findElements(By.xpath("//div[@class=\"d20f4628d0\"]"));
            for (int i = 0; i < hotels.size(); i++) {
                desiredHotel = hotels.get(i).getText();
                if (desiredHotel.contains("olip")){
                    clickOnDesiredHotel();
                    break;
                }
            }
            if (desiredHotel.contains("olip")){
                break;
            }
            clickOnNextPageButton();
        }
//        System.out.println("Found the Hotel, and it's: " + desiredHotel);         //Checkpoint (Done)
    }
}
