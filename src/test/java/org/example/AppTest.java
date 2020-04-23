package org.example;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Unit test for simple App.
 */
public class AppTest
{
    public WebDriver driver = null;
    public String baseUrl = "https://www.hepsiburada.com/";

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver","C:\\webdrivers\\chromedriver.exe");
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("test-type");
        options.addArguments("--start-maximized");
        options.addArguments("--disable-web-security");
        options.addArguments("--allow-running-insecure-content");
        capabilities.setCapability("chrome.binary","C:\\Users\\cahit.evmek\\Desktop\\SelleniumProject\\src\\library\\drivers\\chromedriver.exe");
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        driver = new ChromeDriver(capabilities);

    }

    @Test
    public void correctOpen() throws InterruptedException {
        driver.get(baseUrl);


        //Açık Bekleme Tipi olan WebdriverWait Tanımlanır.
        //Bu, bir TimeoutException özel durumu oluşturmadan önce 15 saniye kadar bekler veya öğenin bulduğu takdirde 0 - 15 saniye içinde döndürür
        //WebDriverWait wait = new WebDriverWait(driver,TimeOut);
        WebDriverWait wait = new WebDriverWait(driver,10);

        Thread.sleep(5000);

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
}

