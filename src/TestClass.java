import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.WebDriver;
import org.junit.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestClass {

    private static WebDriver driver;
    private static ExtentReports extent;
    private static ExtentTest test;

    @BeforeClass
    public static void setUpTest(){
        extent = new ExtentReports();
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(ConstantsClass.PATHWAY_TO_AUXILIARY_FILES+"/extent.html");
        extent.attachReporter(htmlReporter);
        test = extent.createTest("Project Test", "Report of Web Project");

        System.setProperty("webdriver.chrome.driver", "C:/Users/efratbaruch/Desktop/Automation/Selenium/drivers/ChromeDriver.exe");
        try {
            ConstantsClass.DRIVER_TYPE = SupportingMethods.readXML("driverType");
        }catch (Exception e){
            test.log(Status.FAIL, "Problem with external file." + e.getMessage());
        }finally {
            if (ConstantsClass.DRIVER_TYPE.equals("chrome")) {
                driver = new ChromeDriver();
                driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                test.log(Status.PASS, "Driver initialized successfully. Test begins.");
            }
        }
        ConstantsClass.WAIT = new WebDriverWait(driver, 10);
    }

    @Test
    public void test_01_Registration_Screen() {
        test.log(Status.INFO, "Test 01_Registration_Screen started.");
        boolean testSuccess = false;
        try {
        RegistrationScreen register = new RegistrationScreen();
        register.registrationScreenStart(driver, test);
        testSuccess = true;
        } finally {
            if (testSuccess){
                test.log(Status.PASS, "Test 01_Registration_Screen passed.");
            }else{
                test.log(Status.FAIL, "Test 01_Registration_Screen failed.");
            }
        }
    }

    @Test
    public void test_02_Home_Screen(){
        test.log(Status.INFO, "Test 02_Home_Screen started.");
        boolean testSuccess = false;
        try {
            HomeScreen homeScreen = new HomeScreen();
            homeScreen.chooseBetweenOptions(driver, test);
            testSuccess = true;
        } finally {
            if (testSuccess){
                test.log(Status.PASS, "Test 02_Home_Screen passed.");
            }else{
                test.log(Status.FAIL, "Test 02_Home_Screen failed.");
            }
        }
    }

    @Test
    public void test_03_Choose_Gift_Screen(){
        test.log(Status.INFO, "Test 03_Choose_Gift_Screen started.");
        boolean testSuccess = false;
        try {
            ChooseGiftScreen chooseGift = new ChooseGiftScreen();
            chooseGift.chooseGiftScreenStart(driver, test);
            testSuccess = true;
        } finally {
            if (testSuccess){
                test.log(Status.PASS, "Test 03_Choose_Gift_Screen passed.");
            }else{
                test.log(Status.FAIL, "Test 03_Choose_Gift_Screen failed.");
            }
        }
    }

    @Test
    public void test_04_sender_And_Receiver_Information_Screen(){
        test.log(Status.INFO, "Test 04_Sender_And_Receiver_Information_Screen started.");
        boolean testSuccess = false;
        try {
            SenderAndReceiverInformationScreen send = new SenderAndReceiverInformationScreen();
            send.senderAndReceiverInformationScreenStart(driver, test);
            testSuccess = true;
        }finally {
            if (testSuccess){
                test.log(Status.PASS, "Test 04_Sender_And_Receiver_Information_Screen passed.");
            }else {
                test.log(Status.FAIL, "Test 04_Sender_And_Receiver_Information_Screen failed.");
            }
        }
    }

    @AfterClass
    public static void closeTest(){
        try {
            ConstantsClass.WAIT.until(ExpectedConditions.urlContains("payment"));
            test.log(Status.INFO, "Test completed.", MediaEntityBuilder.createScreenCaptureFromPath(SupportingMethods.takeScreenShot(ConstantsClass.PATHWAY_TO_AUXILIARY_FILES + "/FinalScreen", driver)).build());
        } catch (IOException e) {
            e.printStackTrace();
        }
        driver.quit();
        extent.flush();
    }
}
