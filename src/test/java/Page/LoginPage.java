package Page;



import General.generalFunction;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends generalFunction {
    // Page Factory - OR
    @FindBy(name="username")
    WebElement fieldLogin ;

    @FindBy(name="password")
    WebElement fieldPassWord ;

    @FindBy(className="radius")
    WebElement buttonLogin ;

    @FindBy(linkText="Logout")
    WebElement buttonLogout ;

    @FindBy(xpath="//*[@id=\"flash\"]")
    WebElement displayedMessage ;


    @FindBy(xpath="//*[@id=\"content\"]/div/h2")
    WebElement loginTitle ;


    // Initialisation
    public LoginPage(){
        PageFactory.initElements(driver, this);
    }
    public String  getLoginPageTitle() {
        return driver.getTitle();
    }

    public boolean ValidLoginPage(){
        return loginTitle.isDisplayed();
    }

    public void logIn(String userNamy , String passWordy){
        fieldLogin.sendKeys(userNamy);
        fieldPassWord.sendKeys(passWordy);

    }

    public void clickLogin(){
        buttonLogin.click();
    }

}
