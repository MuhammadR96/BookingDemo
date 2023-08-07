package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.example.pages.ConfirmationPage;
import org.example.pages.DesiredHotelPage;
import org.example.pages.HomePage;
import org.example.pages.SearchResultsPage;
import org.example.utilities.DateFormatter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

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
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
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

        //Homepage Actions
        homePage = new HomePage(driver);
        homePage.clickOnDestinationTextBox();
        homePage.typeInDestinationTextBox();
        homePage.chooseCheckInDate();
        homePage.chooseCheckOutDate();
        homePage.clickOnSearchButton();

        //SearchResultsPage Actions
        searchResultsPage = new SearchResultsPage(driver);
        searchResultsPage.findDesiredHotel();

        //DesiredHotelPage Actions
        desiredHotelPage = new DesiredHotelPage(driver);
        desiredHotelPage.clickOnReserveButton();
        desiredHotelPage.clickOnLargeBedRadioButton();
        desiredHotelPage.selectFromAmountDropDownMenu();
        desiredHotelPage.clickOnConfirmReservationButton();

        //ConfirmationPage Actions
        confirmationPage = new ConfirmationPage(driver);
        String actualTitle = confirmationPage.getTitle();
        String expectedTitle = "Booking.com: Your Details";
        Assert.assertEquals(actualTitle, expectedTitle, "Actual title doesn't match expected title");

        //Data preparation for Assertions
        DateFormatter dateFormatter = new DateFormatter();
        String expectedCheckInDate = dateFormatter.changeExcelSheetDatesFormat(homePage.getCheckInDate());
        String actualCheckInDate = confirmationPage.getCheckInDate();
        String expectedCheckOutDate = dateFormatter.changeExcelSheetDatesFormat(homePage.getCheckOutDate());
        String actualCheckOutDate = confirmationPage.getCheckOutDate();

        //Assertions
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(actualCheckInDate, expectedCheckInDate);
        softAssert.assertEquals(actualCheckOutDate, expectedCheckOutDate);
        softAssert.assertTrue(confirmationPage.getHotelDataCard().getText().contains(searchResultsPage.desiredHotel));
        softAssert.assertAll();

        System.out.println("Test Passed Successfully");         //Checkpoint (Done)
    }
}
