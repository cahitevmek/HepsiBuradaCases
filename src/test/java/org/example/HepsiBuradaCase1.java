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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class HepsiBuradaCase1
{
    private WebDriver driver = null;
    private JavascriptExecutor js = null;
    private String baseUrl = "https://www.hepsiburada.com/";
    private WebDriverWait wait = null;
    private String productName = null;
    private String shippingPrice = null;
    private String cartItemPrice = null;
    private String missingPrice = null;

    @Before
    public void before() {

        System.setProperty("webdriver.chrome.driver","C:\\Users\\cahit.evmek\\Desktop\\HepsiBuradaCases\\Driver\\chromedriver.exe");
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("test-type");
        options.addArguments("--start-maximized");
        options.addArguments("--disable-web-security");
        options.addArguments("--allow-running-insecure-content");
        options.addArguments("--disable-notifications");
        options.addArguments("--user-agent=Mozilla/5.0 (WghrXkuMnF) AppleWebKit/5.0 Chrome/8.0 Safari/5.0");
        capabilities.setCapability("chrome.binary","C:\\Users\\cahit.evmek\\Desktop\\SelleniumProject\\src\\library\\drivers\\chromedriver.exe");
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        driver = new ChromeDriver(capabilities);
        js = (JavascriptExecutor) driver;
        wait = new WebDriverWait(driver,10);
    }

    @Test
    public void test() throws InterruptedException {
        //Hespiburada.com erişim
        driver.get(baseUrl);
        Thread.sleep(5000);

        //hesabım ve giriş yap seçim
        clickByXpath("//*[@id=\"myAccount\"]");
        Thread.sleep(2000);
        clickByXpath("//*[@id=\"login\"]");
        Thread.sleep(2000);

        //kullanıcı bilgileri giriş ve login
        writeByXpath("//*[@id=\"email\"]","hepsiburadatest_cahit@yahoo.com");
        writeByXpath("//*[@id=\"password\"]","Cahit123!");
        Thread.sleep(2000);
        clickByXpath("//*[@id=\"form-login\"]/div[4]/button");

        //Kullanıcı giriş işlemi doğrulama
        Thread.sleep(10000);
        textControlByXpath("//*[@id=\"myAccount\"]/span/a/span[1]","Hesabım");

        //ürün arama
        Thread.sleep(2000);
        clickByXpath("//*[@id=\"SearchBoxOld\"]/div/div/div[1]/div[2]/div/input");
        writeByXpath("//*[@id=\"SearchBoxOld\"]/div/div/div[1]/div[2]/div/input","lego");
        clickByClassName("SearchBoxOld-buttonContainer");

        //75 tl altı ürün listelenmesi
        Thread.sleep(5000);
        clickByXpath("//div[@class='range-contain-row left']/input[@class='input free' and @placeholder = 'En çok']");
        writeByXpath("//div[@class='range-contain-row left']/input[@class='input free' and @placeholder = 'En çok']","74");
        clickByXpath("//div[@class='range-contain-row right']/button[@class='button small']");

        //sepete ürün ekleme ve eklenmiş ürünün adının alınması
        Thread.sleep(10000);
        js.executeScript("window.scrollBy(0,500)");
        productName = getTextFromAttributeByXpath("//button[@class='add-to-basket button small']","data-product",0,"productName\":\"", "\",\"categoryId");
        clickByXpath("//button[@class='add-to-basket button small']",0);
        Thread.sleep(5000);

        //Seçilen ürünün doğru olarak eklendiği ‘Sepetim’ sayfasında doğrulanması
        clickByXpath("//span[@id='shoppingCart']");
        Thread.sleep(5000);
        textControlByXpath("//h4[@class='product-name']/*[contains(@class,'hbus')]", productName);

        //Sepete eklenen ürünün kargo bedava olmadığı doğrulanması
        textNotEquelControlByXpath("//div[@class='price']/strong[@data-bind='text: shippingPrice']","0,00");

        //Sepete ne kadarlık ürün eklenirse kargo bedava olacağı bilgisi doğrulanması
        cartItemPrice = driver.findElement(By.xpath("//div[@class='price']/strong[@data-bind='text: cartItemPrice']")).getText();
        cartItemPrice = cartItemPrice.replace(",",".");

        missingPrice = driver.findElement(By.xpath("//p[@data-bind='html: umbrellaCampaignText']")).getText();
        missingPrice = missingPrice.substring(missingPrice.indexOf("Sepetinize ") + 11, missingPrice.indexOf(" TL'lik"));
        Assert.assertEquals(75.00 - Double.parseDouble(cartItemPrice), Double.parseDouble(missingPrice), Math.abs(75.00 - Double.parseDouble(cartItemPrice) - Double.parseDouble(missingPrice)));

        //sepetteki ürünü silme
        for(int i = 0; i< driver.findElements(By.xpath("//*[contains(@class,'btn-delete')]")).size(); i++)
        {
            clickByXpath("//*[contains(@class,'btn-delete')]",i);
            Thread.sleep(5000);
        }
    }

    @After
    public void after() {

        driver.quit();
    }

    //Xpath parçası ile click işlemi
    public void clickByXpath(String Xpath) throws InterruptedException {

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Xpath)));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(Xpath)));
        Thread.sleep(500);
        driver.findElement(By.xpath(Xpath)).click();
    }

    //Xpath parçası webelements listesi içindeki belirlenmiş element değerine click işlemi
    public void clickByXpath(String Xpath, int indexNo) throws InterruptedException {

        Thread.sleep(500);
        driver.findElements(By.xpath(Xpath)).get(indexNo).click();
    }

    //classname ile click işlemi
    public void clickByClassName(String ClassName) throws InterruptedException {

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(ClassName)));
        wait.until(ExpectedConditions.elementToBeClickable(By.className(ClassName)));
        Thread.sleep(500);
        driver.findElement(By.className(ClassName)).click();
    }

    //xpath parçası ile değer girişi
    public void writeByXpath(String Xpath, String text) throws InterruptedException {

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Xpath)));
        driver.findElement(By.xpath(Xpath)).clear();
        Thread.sleep(500);
        driver.findElement(By.xpath(Xpath)).sendKeys(new CharSequence[]{text});
        driver.findElement(By.xpath(Xpath)).sendKeys(new CharSequence[]{Keys.TAB});
    }

    //cllasname ile değer girişi
    public void writeByClassName(String ClassName, String text) throws InterruptedException {

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ClassName)));
        driver.findElement(By.xpath(ClassName)).clear();
        Thread.sleep(500);
        driver.findElement(By.xpath(ClassName)).sendKeys(new CharSequence[]{text});
        driver.findElement(By.xpath(ClassName)).sendKeys(new CharSequence[]{Keys.TAB});
    }

    //bir xpath parçası ile bulunan ekrandaki string değerinin, beklenen string değer ile eşit olma kontrolü
    private void textControlByXpath(String Xpath, String text) throws InterruptedException{

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Xpath)));
        Thread.sleep(500);
        System.out.println(driver.findElement(By.xpath(Xpath)).getText());
        Assert.assertEquals(driver.findElement(By.xpath(Xpath)).getText(), text);
    }

    //bir xpath parçası ile bulunan ekrandaki string değerinin, beklenen string değer ile eşit olmama kontrolü
    private void textNotEquelControlByXpath(String Xpath, String text) throws InterruptedException{

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Xpath)));
        Thread.sleep(500);
        System.out.println(driver.findElement(By.xpath(Xpath)).getText());
        Assert.assertNotEquals(driver.findElement(By.xpath(Xpath)).getText(), text);
    }

    //webelements içindeki belirlenmiş bir değere ait attirbute değerleri içinden bir değerin string formatında alınması
    private String getTextFromAttributeByXpath(String Xpath, String attribute, int indexNo, String firstPartString, String lastPartString) throws InterruptedException{

        //wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Xpath)));
        Thread.sleep(500);
        String s = driver.findElements(By.xpath(Xpath)).get(indexNo).getAttribute(attribute);
        s = s.substring(s.indexOf(firstPartString) + firstPartString.length(),s.indexOf(lastPartString));
        System.out.println(s);
        return s;
    }
}

