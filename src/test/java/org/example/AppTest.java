package org.example;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Unit test for simple App.
 */
public class AppTest
{
    private WebDriver driver = null;
    //private String baseUrl = "https://www.hepsiburada.com/";
    private String baseUrl = "https://www.n11.com/";
    private WebDriverWait wait = null;

    @Before
    public void setUp() {

        System.setProperty("webdriver.chrome.driver","C:\\Users\\cahit.evmek\\Desktop\\HepsiBuradaCases\\Driver\\chromedriver.exe");
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("test-type");
        options.addArguments("--start-maximized");
        options.addArguments("--disable-web-security");
        options.addArguments("--allow-running-insecure-content");
        capabilities.setCapability("chrome.binary","C:\\Users\\cahit.evmek\\Desktop\\SelleniumProject\\src\\library\\drivers\\chromedriver.exe");
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        driver = new ChromeDriver(capabilities);
        wait = new WebDriverWait(driver,10);
    }

    @Test
    public void correctOpen() throws InterruptedException {

        driver.get(baseUrl);

        Thread.sleep(5000);

        click("//*[@id=\"userKvkkModal\"]/div/div[2]/span");
        Thread.sleep(5000);
        textControlByXpath("//*[@id=\"header\"]/div/div/div[2]/div[2]/div[2]/div/div/a[1]","Giriş Yap");
        textControlByClassName("btnSignIn","Giriş Yap");
      //  click("//*[@id=\"header\"]/div/div/div[2]/div[2]/div[2]/div/div/a[1]");

        //*[@id="header"]/div/div/div[2]/div[2]/div[2]/div/div/a[1]


        //hepsi burada
//        textControlByXpath("//*[@id=\"myAccount\"]/span/a/span[2]","Giriş Yap");



        //click("//*[@id=\"myAccount\"]");
        //click("//*[@id=\"login\"]");
       // write("//*[@id=\"email\"]","cahitevmek@yahoo.com");
       // write("//*[@id=\"password\"]","");

     //   Assert.assertEquals(driver.findElement(By.className("btnSignIn")).getAttribute(), "Google");

       // click("//*[@id=\"login-container\"]/section[1]/div/div[2]/div[1]/div/label");
      //  click("//*[@id=\"loginButton\"]");

    //    WebElement kvkk = wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[@id=\"userKvkkModal\"]/div/div[2]/span"))));
    //    kvkk.click();


/*
        WebElement myAccount = wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[@id=\"myAccount\"]"))));
        myAccount.click();

        WebElement login = wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[@id=\"login\"]"))));
        login.click();

        WebElement girisYapCheckBox = wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[@id=\"login-container\"]/section[1]/div/div[2]/div[1]/div/label"))));
        girisYapCheckBox.click();

        WebElement eposta = driver.findElement(By.xpath("//*[@id=\"email\"]"));
        eposta.clear();
        eposta.sendKeys("cahitevmek@yahoo.com");

        WebElement sifre = driver.findElement(By.xpath("//*[@id=\"password\"]"));
        sifre.clear();
        sifre.sendKeys("Harime0055");

        WebElement girisBtn = driver.findElement(By.xpath("//*[@id=\"form-login\"]/div[4]/button"));
        girisBtn.click();

        //     Assert.assertEquals(driver.findElement(By.xpath()), "Google");

        //     WebElement TxtBoxContent = driver.findElement(By.id(WebelementID));

        //   System.out.println("Printing " + TxtBoxContent.getAttribute("value"));

        /*

        //Giriş yap navbarda görünene kadar bekler ve tıklar
        WebElement navBarLogin = wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[@id=\"Header\"]/nav[1]/div/div[8]/a[1]"))));
        navBarLogin.click();

        //Username textbox'ı için veri gönderir
        WebElement username = driver.findElement(By.xpath("/html/body/div[20]/div[2]/div/div/div/form/div[1]/div/div/input"));
        username.sendKeys("test@gmail.com");

        //Password textbox'ı için veri gönderir
        WebElement password = driver.findElement(By.xpath("/html/body/div[20]/div[2]/div/div/div/form/div[2]/div/div/input"));
        password.sendKeys("123456");

        //Giriş Yap Butonuna Tıklar
        WebElement loginClick = wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("/html/body/div[20]/div[2]/div/div/div/form/div[3]/button/span[1]"))));
        loginClick.click();
*/


    }

    @After
    public void tearDown() {
        //      driver.quit();
    }

    public void click(String Xpath) throws InterruptedException {

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Xpath)));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(Xpath)));
        Thread.sleep(500);
        this.driver.findElement(By.xpath(Xpath)).click();
    }

    public void write(String Xpath, String text) throws InterruptedException {

        this.wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Xpath)));
        this.driver.findElement(By.xpath(Xpath)).clear();
        Thread.sleep(500);
        this.driver.findElement(By.xpath(Xpath)).sendKeys(new CharSequence[]{text});
        this.driver.findElement(By.xpath(Xpath)).sendKeys(new CharSequence[]{Keys.TAB});
    }

    public void textControlByClassName(String classname, String text) throws InterruptedException {

        this.wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(classname)));
        Thread.sleep(500);
        Assert.assertEquals(driver.findElement(By.className(classname)).getText(), text);
    }

    private void textControlByXpath(String Xpath, String text) throws InterruptedException{

        this.wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Xpath)));
        Thread.sleep(500);
        Assert.assertEquals(driver.findElement(By.xpath(Xpath)).getText(), text);
    }

}

