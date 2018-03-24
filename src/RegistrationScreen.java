import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.io.IOException;

class RegistrationScreen {

    // The method initializes the methods of the RegistrationScreen.
    void registrationScreenStart (WebDriver driver, ExtentTest test){
        try {
            openWebSite(driver, test);
            registerToWebsite(driver, test);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    // The method opens the website and checks if the url is accessible.
    private void openWebSite(WebDriver driver, ExtentTest test) throws IOException {
        boolean urlRead = true;
        try {
            ConstantsClass.WEBSITE_URL = SupportingMethods.readXML("url");
        } catch (Exception e) {
            test.log(Status.FAIL, "Problem with external file." + e.getMessage());
            urlRead = false;
        }finally {
            if (urlRead) {
                boolean pageUploaded = true;
                try {
                    driver.get(ConstantsClass.WEBSITE_URL);
                } catch (Exception e) {
                    test.log(Status.FATAL, "URL doesn't load.", MediaEntityBuilder.createScreenCaptureFromPath(SupportingMethods.takeScreenShot("."+ConstantsClass.PATHWAY_TO_AUXILIARY_FILES + "/UrlUploadFail", driver)).build());
                    pageUploaded = false;
                } finally {
                    if (pageUploaded) {
                        ConstantsClass.WAIT.until(ExpectedConditions.visibilityOfElementLocated(ConstantsClass.ENTRY_LINK));
                        test.log(Status.PASS, "URL load was successful.", MediaEntityBuilder.createScreenCaptureFromPath(SupportingMethods.takeScreenShot("."+ConstantsClass.PATHWAY_TO_AUXILIARY_FILES + "/EntryToWebsite", driver)).build());
                    }
                }
            }
        }
    }

//     The method registers the tester to the website
    private void registerToWebsite (WebDriver driver, ExtentTest test) throws IOException {
        driver.findElement(ConstantsClass.ENTRY_LINK).click();
        driver.findElement(ConstantsClass.REGISTRATION_LINK).click();
        ConstantsClass.WAIT.until(ExpectedConditions.visibilityOfElementLocated(ConstantsClass.NAME_FIELD));
        test.log(Status.INFO, "Registration screen opened.", MediaEntityBuilder.createScreenCaptureFromPath(SupportingMethods.takeScreenShot("."+ConstantsClass.PATHWAY_TO_AUXILIARY_FILES + "/RegistrationScreen", driver)).build());
        boolean detaiUpload = true;
        try {
            driver.findElement(ConstantsClass.NAME_FIELD).sendKeys(SupportingMethods.readXML("firstName"));
            driver.findElement(ConstantsClass.EMAIL_FIELD).sendKeys(SupportingMethods.readXML("email"));
            driver.findElement(ConstantsClass.PASSWORD_FIELD).sendKeys(SupportingMethods.readXML("password"));
            driver.findElement(ConstantsClass.PASSWORD_VALIDATION_FIELD).sendKeys(SupportingMethods.readXML("password"));
        } catch (Exception e){
            test.log(Status.FAIL, "Problem with external file. Registration not successful" + e.getMessage());
            detaiUpload = false;
        }finally {
            if (detaiUpload){
                driver.findElement(ConstantsClass.AGREEMENT_RADIO_BUTTON).click();
                driver.findElement(ConstantsClass.SPAM_RADIO_BUTTON).click();
                driver.findElement(ConstantsClass.REGISTRATION_BLOCK_ID).submit();
                test.log(Status.PASS, "Registration was successful.");
            }
        }
    }
}
