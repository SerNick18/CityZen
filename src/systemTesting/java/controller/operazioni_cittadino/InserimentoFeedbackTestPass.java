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

public class InserimentoFeedbackTestPass {
    private WebDriver driver;
    private String baseUrl;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();
    private Cittadino cittadino;
    private FacadeDAO service;
    private Segnalazione segnalazione, s;

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
        segnalazione = new Segnalazione();
        segnalazione.setOggetto("Buca in strada");
        segnalazione.setVia("roma");
        segnalazione.setCivico(1);
        segnalazione.setPriorita(0);
        segnalazione.setNumSolleciti(0);
        segnalazione.setStato("chiusa");
        segnalazione.setDataSegnalazione(new Date());
        segnalazione.setFoto("immagineee.jpg");
        segnalazione.setDescrizione("Una buca in via roma");
        segnalazione.setCittadino(cittadino);
        segnalazione.setRiaperta(0);
        service.inserisciSegnalazione(segnalazione);
    }

    @Test
    public void testInserimentoFeedbackTestPass() throws Exception {
        driver.get("http://localhost:8080/CityZen_war_exploded/index.jsp");
        driver.findElement(By.linkText("Accedi")).click();
        driver.findElement(By.id("email")).click();
        driver.findElement(By.id("email")).clear();
        driver.findElement(By.id("email")).sendKeys(cittadino.getEmail());
        driver.findElement(By.id("pwd")).click();
        driver.findElement(By.id("pwd")).clear();
        driver.findElement(By.id("pwd")).sendKeys(cittadino.getPwd());
        driver.findElement(By.id("loginId")).click();
        driver.findElement(By.linkText("Chiuse")).click();
        driver.findElement(By.xpath("(//a[contains(text(),'Inserisci feedback')])[1]")).click();
        driver.findElement(By.id("descrizione")).click();
        driver.findElement(By.id("descrizione")).clear();
        driver.findElement(By.id("descrizione")).sendKeys("bravissimiiiiii grandiii forza scafatiiii SCAFATI REGNAAAA");
        driver.findElement(By.id("inlineCheckbox5")).click();
        driver.findElement(By.id("buttonInoltro")).click();
        driver.findElement(By.id("buttonInoltro")).click();
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

