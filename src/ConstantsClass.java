import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;

class ConstantsClass {

    // general & settings constants
    static final String PATHWAY_TO_AUXILIARY_FILES = "C:/Users/efratbaruch/IdeaProjects/Automating_BuyMe_Website_Project_Efrat_Baruch/Auxiliary Test Files";
    static String DRIVER_TYPE;
    static String WEBSITE_URL;
    static WebDriverWait WAIT;

    // constants for RegistrationScreen class
    static final By ENTRY_LINK = By.partialLinkText("כניסה");
    static final By REGISTRATION_LINK = By.cssSelector("span[class='header-link bold']");
    static final By NAME_FIELD = By.cssSelector("input[placeholder='שם פרטי']");
    static final By EMAIL_FIELD = By.cssSelector("input[placeholder='מייל']");
    static final By PASSWORD_FIELD = By.cssSelector("input[placeholder='סיסמה']");
    static final By PASSWORD_VALIDATION_FIELD = By.cssSelector("input[placeholder='אימות סיסמה']");
    static final By AGREEMENT_RADIO_BUTTON = By.cssSelector("label[for='register-consent']");
    static final By SPAM_RADIO_BUTTON = By.cssSelector("label[for='register-mailing']");
    static final By REGISTRATION_BLOCK_ID = By.cssSelector("form[class='ember-view']");

    // constants for HomeScreen class
    static final By DROP_DOWN_MENUS = By.className("chosen-single");
    static final By OPTION_TO_SELECT = By.cssSelector("li[data-option-array-index='2']");

    // constants for ChooseGiftScreen class
    static String CHOOSE_GIFT_SCREEN_URL = "https://buyme.co.il/search?budget=2&category=16&region=11";
    static final By GIFT_TO_CHOOSE = By.className("icon-plus");

    // constants for SenderAndReceiverInformationScreen class
    static final By AMOUNT_TO_GIVE_FIELD = By.cssSelector("input[placeholder='מה הסכום?']");
    static final By FOR_FRIEND_BUTTON = By.cssSelector("label[for='for-friend-radio']");
    static final By RECEIVER_FIELD = By.cssSelector("input[maxlength='25']");
    static final By SENDER_FIELD = By.cssSelector("input[maxlength='30']");
    static final By BLESS_FIELD = By.id("msg");
    static final By PHOTO_UPLOAD_FIELD = By.name("fileUpload");
    static final By SEND_AFTER_PAYMENT_BUTTON = By.className("send-now");
    static final By SEND_BY_EMAIL = By.cssSelector("span[class='icon icon-envelope']");
    static final By RECEIVER_EMAIL_FIELD = By.cssSelector("input[type='email']");
    static final By PAYMENT_BUTTON = By.cssSelector("div[class=submit-wrapper]");
}

