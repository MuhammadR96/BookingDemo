package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.example.pages.ConfirmationPage;
import org.example.pages.DesiredHotelPage;
import org.example.pages.HomePage;
import org.example.pages.SearchResultsPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class MainTest {


    WebDriver driver;
    HomePage homePage;
    SearchResultsPage searchResultsPage;
    DesiredHotelPage desiredHotelPage;
    ConfirmationPage confirmationPage;

    @BeforeTest
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://booking.com");
    }

    @AfterTest
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void mainTest() throws InterruptedException, IOException {

        homePage = new HomePage(driver);
        Thread.sleep(1000);
        homePage.clickOnDestinationTextBox();
        homePage.typeInDestinationTextBox("Alexandria");
        homePage.clickOnCheckInDateDropDownMenu();
        homePage.selectDate();
        Thread.sleep(1000);
        homePage.chooseCheckInDate();
        homePage.chooseCheckOutDate();
        homePage.clickOnSearchButton();

        searchResultsPage = new SearchResultsPage(driver);
        searchResultsPage.findDesiredHotel();
        Thread.sleep(2500);

        desiredHotelPage = new DesiredHotelPage(driver);
        desiredHotelPage.clickOnReserveButton();
        desiredHotelPage.clickOnLargeBedRadioButton();
        desiredHotelPage.selectFromAmountDropDownMenu();
        desiredHotelPage.clickOnConfirmReservationButton();

        confirmationPage = new ConfirmationPage(driver);
        String actualTitle = confirmationPage.getTitle();
        String expectedTitle = "Booking.com: Your Details";
        Assert.assertEquals(actualTitle, expectedTitle, "Actual title doesn't match expected title");
        System.out.println("Test Passed Successfully");
    }
}
