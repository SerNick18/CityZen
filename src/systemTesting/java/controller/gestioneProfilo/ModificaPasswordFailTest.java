package controller.gestioneProfilo;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;

import model.gestioneDati.facadeDataAccess.FacadeDAO;
import model.gestioneDati.modelObjects.Cittadino;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class ModificaPasswordFailTest {
    private WebDriver driver;
    private String baseUrl;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();
    private Cittadino cittadino;
    private FacadeDAO service;

    @Before
    public void setUp() throws Exception {
        System.setProperty("webdriver.gecko.driver","C:\\driver\\geckodriver.exe");
        driver = new FirefoxDriver();
        baseUrl = "https://www.google.com/";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        service = new FacadeDAO();
        cittadino = new Cittadino("CPNLLD11S19A489D", "Giuseppe", "Cattaneo", "Pippotto8.",
                "via roma",3,"Fisciano","cattaneo@gmail.com",0,0);
        service.registraCittadino(cittadino);
    }

    @Test
    public void testModificaPasswordFail() throws Exception {
        driver.get("http://localhost:8080/CityZen_war_exploded/");
        driver.findElement(By.linkText("Accedi")).click();
        driver.findElement(By.id("email")).click();
        driver.findElement(By.id("email")).clear();
        driver.findElement(By.id("email")).sendKeys(cittadino.getEmail());
        driver.findElement(By.id("pwd")).click();
        driver.findElement(By.id("pwd")).clear();
        driver.findElement(By.id("pwd")).sendKeys(cittadino.getPwd());
        driver.findElement(By.id("loginId")).click();
        driver.findElement(By.linkText("Profilo")).click();
        driver.findElement(By.xpath("//input[@value='Modifica Password']")).click();
        driver.findElement(By.id("oldPass")).click();
        driver.findElement(By.id("oldPass")).clear();
        driver.findElement(By.id("oldPass")).sendKeys(cittadino.getPwd());
        driver.findElement(By.id("pwd1")).click();
        driver.findElement(By.id("pwd1")).clear();
        driver.findElement(By.id("pwd1")).sendKeys("Pippotto8.");
        driver.findElement(By.id("pwd2")).click();
        driver.findElement(By.id("pwd2")).clear();
        driver.findElement(By.id("pwd2")).sendKeys("Pippotto5.");
        driver.findElement(By.xpath("//button[@type='submit']")).click();
    }

    @After
    public void tearDown() throws Exception {
        service.eliminaCittadino(cittadino.getCF());
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