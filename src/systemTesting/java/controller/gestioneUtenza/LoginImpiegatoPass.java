package controller.gestioneUtenza;


import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;

import model.gestioneDati.facadeDataAccess.FacadeDAO;
import model.gestioneDati.modelObjects.Impiegato;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class LoginImpiegatoPass {
    private WebDriver driver;
    private String baseUrl;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();
    FacadeDAO service = new FacadeDAO();
    Impiegato impiegato;

    @Before
    public void setUp() throws Exception {
        System.setProperty("webdriver.gecko.driver","C:\\driver\\geckodriver.exe");
        driver = new FirefoxDriver();
        baseUrl = "https://www.google.com/";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
         impiegato = new Impiegato("asdf1@scafati.it", "12345", "Password1", "FRDLSS00C01A509O", "Antonio", "Scafati",
                "Pirandello", 33, "Milano", 10, 7);
         service.inserisciImpiegato(impiegato);
    }

    @Test
    public void testLoginImpiegato() throws Exception {
        driver.get("http://localhost:8080/CityZen_war_exploded/index.jsp");
        driver.findElement(By.linkText("Accedi")).click();
        driver.findElement(By.id("email")).click();
        driver.findElement(By.id("email")).clear();
        driver.findElement(By.id("email")).sendKeys(impiegato.getEmail());
        driver.findElement(By.id("pwd")).clear();
        driver.findElement(By.id("pwd")).sendKeys(impiegato.getPwd());
        driver.findElement(By.id("loginAccesso")).submit();
    }

    @After
    public void tearDown() throws Exception {
        service.eliminaImpiegato(impiegato.getMatricola());
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

