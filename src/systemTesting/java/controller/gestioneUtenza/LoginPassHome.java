package controller.gestioneUtenza;

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

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class LoginPassHome {
    private WebDriver driver;
    private String baseUrl;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();
    FacadeDAO service = new FacadeDAO();
    Cittadino cittadino;

    @Before
    public void setUp() throws Exception {
        System.setProperty("webdriver.gecko.driver","C:\\driver\\geckodriver.exe");
        driver = new FirefoxDriver();
        baseUrl = "https://www.google.com/";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        cittadino = new Cittadino("SBAFNC98T26H703S","Francesco","Sabia","Password1!",
                "via europa",12,"Salerno","abcd98@gmail.com",0,0);
        service.registraCittadino(cittadino);
    }

    @Test
    public void testUntitledTestCase() throws Exception {
        driver.get("http://localhost:8080/CityZen_war_exploded/login.jsp");
        driver.findElement(By.id("email")).click();
        driver.findElement(By.id("email")).clear();
        driver.findElement(By.id("email")).sendKeys(cittadino.getEmail());
        driver.findElement(By.id("pwd")).click();
        driver.findElement(By.id("pwd")).clear();
        driver.findElement(By.id("pwd")).sendKeys(cittadino.getPwd());
        driver.findElement(By.xpath("//body")).click();
        driver.findElement(By.id("loginId")).click();
        driver.findElement(By.linkText("Home")).click();
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
