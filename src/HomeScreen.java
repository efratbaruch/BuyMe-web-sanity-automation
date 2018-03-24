import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


import java.io.IOException;
import java.util.List;

class HomeScreen {

    // The method chooses options from dropdown menus and submits them.
    void chooseBetweenOptions(WebDriver driver, ExtentTest test) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<WebElement> dropDownOptions = driver.findElements(ConstantsClass.DROP_DOWN_MENUS);
        for (int i=0; i<3; i++) {
            dropDownOptions.get(i).click();
            driver.findElement(ConstantsClass.OPTION_TO_SELECT).click();
            test.log(Status.PASS, "Option on dropDown menu number " + (i+1) + " selected successfully.");
        }
        try {
            test.log(Status.INFO, "Options selected.", MediaEntityBuilder.createScreenCaptureFromPath(SupportingMethods.takeScreenShot("."+ConstantsClass.PATHWAY_TO_AUXILIARY_FILES + "/HomeScreenWithChoices", driver)).build());
        } catch (IOException e) {
            e.printStackTrace();
        }
        driver.findElement(By.cssSelector("button[type='submit']")).click();
    }
}


