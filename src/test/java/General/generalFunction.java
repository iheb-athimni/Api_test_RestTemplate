package General;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Properties;

public class generalFunction{

    private ThreadLocal<WebDriver> webDriver ;
    public static long PAGE_LOAD_TIMEOUT = 20;
    public static long IMPLICIT_WAIT = 10;
    public static WebDriver driver ;
    public static Properties prop ;



    public generalFunction(){

        }

    public void propertySet(){
        driver = null ;
        try {
            prop = new Properties();
            loadProperties(new File(readFileFromResources("src/test/java/configs/config.properties")));
        } catch (IOException exp) {
            exp.printStackTrace();
        }
    }

    public void loadProperties(File file) throws IOException {
        prop.load(new FileInputStream(file));
    }

    //find the property file from the path and return exact Path
    public String readFileFromResources(String uri) throws FileNotFoundException {
        File toReturn = ResourceUtils.getFile(uri);
        boolean exist = toReturn.exists();
        if (!exist) {
            try {
                toReturn = new File(getClass().getClassLoader().getResource(uri).toURI());
            }catch (URISyntaxException exp)
            {
                exp.printStackTrace();
            }
        }
        return toReturn.getAbsolutePath();
    }

    public static void getTheDriver(String theDriver){

        String BrowserName = prop.getProperty("browser");
        //if(BrowserName.equals(("chrome"))) {
        if(theDriver.equals(("chrome"))) {
            System.setProperty("webdriver.chrome.driver","drivers/chromedriver.exe" );
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--incognito");
            options.addArguments("--headless");
            driver = new ChromeDriver(options);
//            driver = new ChromeDriver();

        //}else if (BrowserName.equals(("fireFox"))) {
        }else if (theDriver.equals(("fireFox"))) {
            System.setProperty("webdriver.gecko.driver","drivers/geckodriver.exe" );
            driver = new FirefoxDriver();
        }

        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
    }
    public void openUrl(String ult ) throws IOException {
        driver.get(ult);
    }

    public static void removeDriver(){
        if(driver != null){
            driver.quit();
            driver = null ;
        }
    }
}
