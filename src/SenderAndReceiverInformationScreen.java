import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.io.IOException;

class SenderAndReceiverInformationScreen {

    // The method initializes the methods of the SenderAndReceiverInformationScreen.
    void senderAndReceiverInformationScreenStart (WebDriver driver, ExtentTest test){
        try {
            inputAmount(driver, test);
            uploadInformation(driver, test);
            uploadPicture(driver, test);
            sendByEmail(driver, test);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    // The method inputs amount to gift and saves a screen shot.
    private void inputAmount (WebDriver driver, ExtentTest test) throws IOException {
        ConstantsClass.WAIT.until(ExpectedConditions.visibilityOfElementLocated(ConstantsClass.AMOUNT_TO_GIVE_FIELD));
        test.log(Status.INFO, "Amount to gift screen.", MediaEntityBuilder.createScreenCaptureFromPath(SupportingMethods.takeScreenShot(ConstantsClass.PATHWAY_TO_AUXILIARY_FILES + "/AmountToGiftScreen", driver)).build());
        driver.findElement(ConstantsClass.AMOUNT_TO_GIVE_FIELD).sendKeys("200" + Keys.ENTER);
    }

    // The method uploads information for sender and receiver of gift and saves a screen shot.
    private void uploadInformation(WebDriver driver, ExtentTest test) throws IOException {
        ConstantsClass.WAIT.until(ExpectedConditions.visibilityOfElementLocated(ConstantsClass.FOR_FRIEND_BUTTON));
        test.log(Status.INFO, "Sender and receiver information screen.", MediaEntityBuilder.createScreenCaptureFromPath(SupportingMethods.takeScreenShot(ConstantsClass.PATHWAY_TO_AUXILIARY_FILES + "/SenderReceiverInformationScreen", driver)).build());
        driver.findElement(ConstantsClass.FOR_FRIEND_BUTTON).click();
        boolean detailUpload = true;
        try {
            driver.findElement(ConstantsClass.RECEIVER_FIELD).sendKeys(SupportingMethods.readXML("receiverOfGift"));
            driver.findElement(ConstantsClass.SENDER_FIELD).clear();
            driver.findElement(ConstantsClass.SENDER_FIELD).sendKeys(SupportingMethods.readXML("firstName"));
            driver.findElement(ConstantsClass.BLESS_FIELD).sendKeys(SupportingMethods.readXML("bless"));
        } catch (Exception e) {
            test.log(Status.FAIL, "Problem with external file. Information not uploaded" + e.getMessage());
            detailUpload = false;
        } finally {
            if (detailUpload) {
                test.log(Status.PASS, "Information uploaded successfully.");
            }
        }
    }

    // The method uploads a picture to the website.
    private void uploadPicture (WebDriver driver, ExtentTest test){
        boolean pictureUpload = false;
        try {
            driver.findElement(ConstantsClass.PHOTO_UPLOAD_FIELD).sendKeys(ConstantsClass.PATHWAY_TO_AUXILIARY_FILES + "/Best Wishes Photo.png");
            pictureUpload = true;
        } finally {
            if (pictureUpload){
                test.log(Status.PASS, "Picture uploaded successfully.");
            } else {
                test.log(Status.FAIL, "Picture upload failed.");
            }
        }
    }

    // The method chooses sending the gift by email and uploads the receiver email details.
    private void sendByEmail (WebDriver driver, ExtentTest test) throws IOException{
        driver.findElement(ConstantsClass.SEND_AFTER_PAYMENT_BUTTON).click();
        driver.findElement(ConstantsClass.SEND_BY_EMAIL).click();
        boolean detailUpload = true;
        try {
            driver.findElement(ConstantsClass.RECEIVER_EMAIL_FIELD).sendKeys(SupportingMethods.readXML("friendEmail") + Keys.ENTER);
        }catch (Exception e){
            test.log(Status.FAIL, "Problem with external file. Receiver email not uploaded." + e.getMessage());
            detailUpload = false;
        }finally {
            if(detailUpload){
                test.log(Status.PASS, "Receiver email uploaded and saved successfully.");
            }
        }
        driver.findElement(ConstantsClass.PAYMENT_BUTTON).click();
    }
}
