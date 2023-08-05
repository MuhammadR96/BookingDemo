package org.example.pages;

import org.example.utilities.ExcelReaderMine;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;


public class HomePage {


    //Constructor
    public HomePage(WebDriver driver) {
        this.driver = driver;
    }


    WebDriver driver;

    //Refactor and make it Data-Driven
    String checkInDate = "1 October 2023";
    String checkOutDate = "15 October 2023";


    //Locators
    By destinationTextBoxLocator = By.id(":rc:");
    By checkInDateDropDownMenuLocator = By.xpath("//button[@data-testid=\"date-display-field-start\"]");
    By checkInDateLocator = By.xpath("//span[@aria-label=\"" + checkInDate + "\"]");
    By nextPageButtonLocator = By.xpath("//button[@class=\"fc63351294 a822bdf511 e3c025e003 fa565176a8 cfb238afa1 c334e6f658 ae1678b153 c9fa5fc96d be298b15fa\"]");
    By checkOutDateLocator = By.xpath("//span[@aria-label=\"" + checkOutDate + "\"]");
    By searchButtonLocator = By.xpath("//span[contains(text(),'Search')]");


    //Actions
    private WebElement getDestinationTextBox() {
        return driver.findElement(destinationTextBoxLocator);
    }

    public void clickOnDestinationTextBox() {
        Actions actions = new Actions(driver);
        WebElement destinationTextBox = getDestinationTextBox();
        actions.moveToElement(destinationTextBox).build().perform();
        actions.click(destinationTextBox).build().perform();
    }

    public void typeInDestinationTextBox(String destination) {
        WebElement destinationTextBox = getDestinationTextBox();
        destinationTextBox.sendKeys(destination);
    }

    private WebElement getCheckInDateDropDownMenuElement() {
        return driver.findElement(checkInDateDropDownMenuLocator);
    }

    public void clickOnCheckInDateDropDownMenu() {
        Actions actions = new Actions(driver);
        WebElement CheckInDateDropDownMenu = getCheckInDateDropDownMenuElement();
        actions.moveToElement(CheckInDateDropDownMenu).build().perform();
        actions.click(CheckInDateDropDownMenu).build().perform();
    }


    //Needs Refactoring (String Date) & nextPageButton clicking
    public void chooseCheckInDate() {
        WebElement checkInDateElement = driver.findElement(checkInDateLocator);
        if (checkInDateElement.isDisplayed()) {
            checkInDateElement.click();
        } else if (!checkInDateElement.isDisplayed()) {
            clickOnNextPageButton();
            checkInDateElement.click();
        }
    }

    public void chooseCheckOutDate() {
        WebElement checkOutDateElement = driver.findElement(checkOutDateLocator);
        if (checkOutDateElement.isDisplayed()) {
            checkOutDateElement.click();
        } else if (!checkOutDateElement.isDisplayed()) {
            clickOnNextPageButton();
            checkOutDateElement.click();
        }
    }

    private void clickOnNextPageButton() {
        WebElement nextPageButton = driver.findElement(nextPageButtonLocator);
        nextPageButton.click();
    }

    public void clickOnSearchButton() {
        WebElement searchButton = driver.findElement(searchButtonLocator);
        searchButton.click();
    }

    //Refactor selectDate(String month_year, String select_day)
    public void selectDate() throws InterruptedException {
        List<WebElement> months;
        String currentMonth;
        Calendar cal = Calendar.getInstance();
        currentMonth = cal.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.ENGLISH);

        while (!currentMonth.contains("Oct")){
            months = driver.findElements(By.xpath("//h3[@class=\"ac78a73c96 ab0d1629e5\"]"));
            for (int i = 0; i < months.size(); i++) {
                currentMonth = months.get(i).getText();
//                System.out.println(currentMonth);
            }
            clickOnNextPageButton();
        }
//        System.out.println("Found the Month, and it's: " + currentMonth);
    }


}
