package org.example.pages;

import org.example.utilities.Constants;
import org.example.utilities.DateFormatter;
import org.example.utilities.ExcelReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;


public class HomePage {


    //Constructor
    public HomePage(WebDriver driver) throws IOException {
        this.driver = driver;
    }


    WebDriver driver;


    DateFormatter dateFormatter;
    //Refactor and make it Data-Driven (Done)
    ExcelReader excelReader = new ExcelReader(Constants.EXCEL_FILE_NAME);
    String checkInDate = excelReader.getCellData(Constants.SHEET_NAME, Constants.ROW, Constants.CHECK_IN_DATE);
    String checkOutDate = excelReader.getCellData(Constants.SHEET_NAME, Constants.ROW, Constants.CHECK_OUT_DATE);
    String destination = excelReader.getCellData(Constants.SHEET_NAME, Constants.ROW, Constants.LOCATION);



    //Locators
    private By destinationTextBoxLocator = By.id(":rc:");
    private By checkInDateDropDownMenuLocator = By.xpath("//button[@data-testid=\"date-display-field-start\"]");
    private By checkInDateLocator = By.xpath("//span[@aria-label=\"" + checkInDate + "\"]");
    private By nextPageButtonLocator = By.xpath("//button[@class=\"fc63351294 a822bdf511 e3c025e003 fa565176a8 cfb238afa1 c334e6f658 ae1678b153 c9fa5fc96d be298b15fa\"]");
    private By checkOutDateLocator = By.xpath("//span[@aria-label=\"" + checkOutDate + "\"]");
    private By searchButtonLocator = By.xpath("//span[contains(text(),'Search')]");


    //Actions
    public WebElement getDestinationTextBox() {
        return driver.findElement(destinationTextBoxLocator);
    }

    public void clickOnDestinationTextBox() {
        Actions actions = new Actions(driver);
        WebElement destinationTextBox = getDestinationTextBox();
        actions.moveToElement(destinationTextBox).build().perform();
        actions.click(destinationTextBox).build().perform();
    }

    public void typeInDestinationTextBox() {
        WebElement destinationTextBox = getDestinationTextBox();
        destinationTextBox.sendKeys(this.destination);
    }

    public WebElement getCheckInDateDropDownMenuElement() {
        return driver.findElement(checkInDateDropDownMenuLocator);
    }

    private void clickOnCheckInDateDropDownMenu() {
        Actions actions = new Actions(driver);
        WebElement CheckInDateDropDownMenu = getCheckInDateDropDownMenuElement();
        actions.moveToElement(CheckInDateDropDownMenu).build().perform();
        actions.click(CheckInDateDropDownMenu).build().perform();
    }


    private void clickOnCheckInDate() {
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


    private void selectDate() throws InterruptedException {
        List<WebElement> months;
        String currentMonth;
        Calendar cal = Calendar.getInstance();
        currentMonth = cal.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.ENGLISH);

        while (!currentMonth.contains(checkInDate.replaceAll("\\d", "").trim())){           //regex to trim Month name out of checkInDate
            months = driver.findElements(By.xpath("//h3[@class=\"ac78a73c96 ab0d1629e5\"]"));
            for (int i = 0; i < months.size(); i++) {
                currentMonth = months.get(i).getText();
//                System.out.println(currentMonth);         //(Checkpoint) (Done)
            }
            clickOnNextPageButton();
        }
//        System.out.println("Found the Month, and it's: " + currentMonth);      //(Checkpoint) (Done)
    }

    //Bundling together all three actions needed to choose Check-in Date to simplify interfacing in MainTest
    public void chooseCheckInDate() throws InterruptedException {
        clickOnCheckInDateDropDownMenu();
        selectDate();
        clickOnCheckInDate();
    }

    public String getCheckInDate() {
        return checkInDate;
    }

    public String getCheckOutDate() {
        return checkOutDate;
    }
}
