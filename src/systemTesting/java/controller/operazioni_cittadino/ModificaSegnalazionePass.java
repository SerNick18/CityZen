package controller.operazioni_cittadino;

import java.util.Date;
import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;

import model.gestioneDati.facadeDataAccess.FacadeDAO;
import model.gestioneDati.modelObjects.Cittadino;
import model.gestioneDati.modelObjects.Segnalazione;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class ModificaSegnalazionePass {
    private WebDriver driver;
    private String baseUrl;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();
    private Cittadino cittadino;
    private FacadeDAO service;
    private Segnalazione segnalazione;

    @Before
    public void setUp() throws Exception {
        service = new FacadeDAO();
        cittadino = new Cittadino("FRSGSP99L28B964R", "Giuseppe", "Fresco", "Prova123",
                "via roma", 3, "Scafati", "abcdef@prova.com", 0, 0);
        service.registraCittadino(cittadino);
        segnalazione = new Segnalazione();
        segnalazione.setVia("roma");
        segnalazione.setCivico(3);
        segnalazione.setPriorita(0);
        segnalazione.setNumSolleciti(0);
        segnalazione.setStato("inoltrata");
        segnalazione.setDataSegnalazione(new Date());
        segnalazione.setDescrizione("grossa fuoriuscita d'acqua");
        segnalazione.setOggetto("testSegnalazione");
        segnalazione.setFoto("immagine.png");
        segnalazione.setRiaperta(0);
        segnalazione.setCittadino(cittadino);
        service.inserisciSegnalazione(segnalazione);
        System.setProperty("webdriver.gecko.driver","C:\\driver\\geckodriver.exe");
        driver = new FirefoxDriver();
        baseUrl = "https://www.google.com/";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void testModificaSegnalazionePass() throws Exception {
        driver.get("http://localhost:8080/CityZen_war_exploded/");
        driver.findElement(By.linkText("Accedi")).click();
        driver.findElement(By.id("email")).click();
        driver.findElement(By.id("email")).clear();
        driver.findElement(By.id("email")).sendKeys(cittadino.getEmail());
        driver.findElement(By.id("pwd")).click();
        driver.findElement(By.id("pwd")).clear();
        driver.findElement(By.id("pwd")).sendKeys(cittadino.getPwd());
        driver.findElement(By.id("loginAccesso")).submit();
        driver.findElement(By.linkText("Le mie segnalazioni")).click();
        driver.findElement(By.linkText("testSegnalazione")).click();
        driver.findElement(By.name("approva")).click();
        driver.findElement(By.id("civico")).click();
        driver.findElement(By.id("civico")).clear();
        driver.findElement(By.id("civico")).sendKeys("33");
        driver.findElement(By.id("buttonInoltro")).click();
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
