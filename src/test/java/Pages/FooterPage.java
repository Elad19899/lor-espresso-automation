package Pages;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class FooterPage extends BasePage {
    public FooterPage(WebDriver driver) {

        super(driver);
    }

    public FooterPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }


    // WebElements definition
    @FindBy(name = "contactFirstName")
    WebElement contactFirstName;
    @FindBy(name = "contactLastName")
    WebElement contactLastName;
    @FindBy(name = "contactEmail")
    WebElement contactEmail;
    @FindBy(name = "contactComment")
    WebElement contactComment;
    @FindBy(className = "list-item")
    List<WebElement> listIteml;
    @FindBy(id = "contact-topic")
    WebElement contactTopicDropDownMenu;
    @FindBy(className = "faq-question")
    List<WebElement>  questions ;

    //
    @FindBy(className = "faq-title")
    WebElement title;

    @FindBy(css = ".page-title span")
    WebElement byTitle;

    //הרשמה לרשימת תפוצה
    @FindBy(name = "hpEmailSignUp")
    WebElement hpEmailSignUp;
    @FindBy(className = "custom-control-label")
    WebElement customControlLabel;
    @FindBy(className = "affirm")
    WebElement acceptBtn;
    @FindBy(className = "subscribe-email")
    WebElement subscribeEmail;

    // Variable definition
    SoftAssert softAssert = new SoftAssert();


    private String returnWebElement(String value) {
        js.executeScript("window.scrollBy(0,5000)");
        String element = null;
        for (WebElement webElement : listIteml) {
            if (webElement.getText().equalsIgnoreCase(value)) {
                element = webElement.getText();
                System.out.println(webElement.getText());
                break;
            }
        }
        return element;
    }

    public void getLinkValue(String value) {
        waitAndClick(driver.findElement(By.partialLinkText(returnWebElement(value))));

    }



    public void ContactUsFormFill() {
        getLinkValue("צרו קשר");
        FooterPage.sendKeys(contactFirstName, "Test");
        FooterPage.sendKeys(contactLastName, "Test");
        FooterPage.sendKeys(contactEmail, "Test@gmail.com");
        Select mySelection = new Select(contactTopicDropDownMenu);
        mySelection.selectByValue("ביטול הזמנה");
        FooterPage.sendKeys(contactComment, "Test Test Test");


    }




    public void FrequentlyAskedQuestionsCheck (){
        getLinkValue("שאלות נפוצות");
        System.out.println(questions.size());
        for(WebElement element : questions){
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            jsClick(element);
        }

    }
    public void hpEmailSignUp() {
        String mail = "@gmail.com";
        String generatedString = RandomStringUtils.randomAlphabetic(6);
        System.out.println(generatedString + mail);
        hpEmailSignUp.sendKeys(generatedString + mail);
        BasePage.waitAndClick(customControlLabel);
        subscribeEmail.click();
    }

    public String getTitle() {
        return title.getText();
    }

    public String getByTitle() {
        return byTitle.getText();
    }
}
