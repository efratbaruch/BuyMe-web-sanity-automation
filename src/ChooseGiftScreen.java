import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.io.IOException;

class ChooseGiftScreen {

    // The method initializes the methods of the ChooseGiftScreen.
    void chooseGiftScreenStart(WebDriver driver, ExtentTest test){
        assertUrl(ConstantsClass.CHOOSE_GIFT_SCREEN_URL, driver, test);
        chooseGift(driver, test);
    }

    // The method Asserts current URL with expected URL and reports the outcome.
    private void assertUrl (String url, WebDriver driver, ExtentTest test){
        ConstantsClass.WAIT.until(ExpectedConditions.urlContains("search"));
        try {
            Assert.assertEquals(url, driver.getCurrentUrl());
            test.log(Status.PASS, "Assertion of URL passed with success.");
        } catch (AssertionError e){
            test.log(Status.FAIL, "Assertion of URL failed."+e.getMessage());
        }
    }

    // The method chooses a gift on the screen and saves a screen shot.
    private void chooseGift (WebDriver driver, ExtentTest test){
        try {
            test.log(Status.INFO, "Choose Gift screen." ,MediaEntityBuilder.createScreenCaptureFromPath(SupportingMethods.takeScreenShot(ConstantsClass.PATHWAY_TO_AUXILIARY_FILES +"/ChooseGiftScreen", driver)).build());
        } catch (IOException e) {
            e.printStackTrace();
        }
        driver.findElement(ConstantsClass.GIFT_TO_CHOOSE).click();
    }

}
