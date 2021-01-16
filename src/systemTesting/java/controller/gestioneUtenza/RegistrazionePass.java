package controller.gestioneUtenza;


import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class RegistrazionePass {
    private WebDriver driver;
    private String baseUrl;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();

    @Before
    public void setUp() throws Exception {
        System.setProperty("webdriver.gecko.driver","C:\\driver\\geckodriver.exe");
        driver = new FirefoxDriver();
        baseUrl = "https://www.google.com/";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void testRegistrazionePass() throws Exception {
        driver.get("http://localhost:8080/CityZen_war_exploded/index.jsp");
        driver.findElement(By.linkText("Registrati")).click();
        driver.findElement(By.id("nome")).click();
        driver.findElement(By.id("nome")).clear();
        driver.findElement(By.id("nome")).sendKeys("Francesco");
        driver.findElement(By.id("cognome")).clear();
        driver.findElement(By.id("cognome")).sendKeys("Sabia");
        driver.findElement(By.id("email")).clear();
        driver.findElement(By.id("email")).sendKeys("asgf99@gmail.com");
        driver.findElement(By.id("cf")).clear();
        driver.findElement(By.id("cf")).sendKeys("SBAFNC98T26H703R");
        driver.findElement(By.id("via")).clear();
        driver.findElement(By.id("via")).sendKeys("antonio");
        driver.findElement(By.id("civico")).clear();
        driver.findElement(By.id("civico")).sendKeys("5");
        driver.findElement(By.id("citta")).clear();
        driver.findElement(By.id("citta")).sendKeys("salerno");
        driver.findElement(By.id("pwd1")).clear();
        driver.findElement(By.id("pwd1")).sendKeys("Password1");
        driver.findElement(By.id("pwd2")).clear();
        driver.findElement(By.id("pwd2")).sendKeys("Password1");
        driver.findElement(By.id("registrami")).click();
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }

    private boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    private boolean isAlertPresent() {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    private String closeAlertAndGetItsText() {
        try {
            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            if (acceptNextAlert) {
                alert.accept();
            } else {
                alert.dismiss();
            }
            return alertText;
        } finally {
            acceptNextAlert = true;
        }
    }
}

