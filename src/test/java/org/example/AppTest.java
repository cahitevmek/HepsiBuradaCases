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

/**
 * Unit test for simple App.
 */
public class AppTest
{
    private WebDriver driver = null;
    private JavascriptExecutor js = null;
    private String baseUrl = "https://www.hepsiburada.com/";
    //private String baseUrl = "https://www.n11.com/";
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
        //clickByXpath("//*[@id=\"SearchBoxOld\"]/div/div/div[2]");
        clickByClassName("SearchBoxOld-buttonContainer");

        //75 tl altı ürün listelenmesi
        Thread.sleep(5000);
        clickByXpath("//div[@class='range-contain-row left']/input[@class='input free' and @placeholder = 'En çok']");
        writeByXpath("//div[@class='range-contain-row left']/input[@class='input free' and @placeholder = 'En çok']","75");
        clickByXpath("//div[@class='range-contain-row right']/button[@class='button small']");

        //sepete ürün ekleme ve eklenmiş ürünün adının alınması
        Thread.sleep(10000);
     /*   WebElement addBasket = driver.findElement(By.xpath("//button[@class='add-to-basket button small']"));
        productName = addBasket.getAttribute("data-product");
        productName = productName.substring(productName.lastIndexOf("productName\":\"") + 14,productName.indexOf("\",\"categoryId"));
        System.out.println(productName);
     */
        js.executeScript("window.scrollBy(0,500)");
        productName = getTextFromAttributeByXpath("//button[@class='add-to-basket button small']","data-product","productName\":\"", "\",\"categoryId");

        clickByXpath("//button[@class='add-to-basket button small']",0);
        Thread.sleep(5000);

        //sepetim açma
        clickByXpath("//span[@id='shoppingCart']");

        Thread.sleep(5000);

        //sepete eklenen ürünün kontrolü
        textControlByXpath("//h4[@class='product-name']/*[contains(@class,'hbus')]", productName);

        //Kargo ücreti alınma kontrolü
        textNotEquelControlByXpath("//div[@class='price']/strong[@data-bind='text: shippingPrice']","0,00");

        cartItemPrice = driver.findElement(By.xpath("//div[@class='price']/strong[@data-bind='text: cartItemPrice']")).getText();
        cartItemPrice = shippingPrice.replace(",",".");
        System.out.println(cartItemPrice);

        missingPrice = driver.findElement(By.xpath("//p[@data-bind='html: umbrellaCampaignText']")).getText();
        missingPrice = missingPrice.substring(missingPrice.indexOf("Sepetinize ") + 11, missingPrice.indexOf(" TL'lik"));
        System.out.println(missingPrice);

        Assert.assertEquals(75 - Double.parseDouble(cartItemPrice), Double.parseDouble(missingPrice));


        clickByXpath("//*[contains(@class,'btn-delete')]");

    }

    @After
    public void after() {
        //      driver.quit();
    }

    public List<WebElement> findElements(String Xpath) throws InterruptedException {

        List<WebElement> elements = driver.findElements(By.xpath(Xpath));

        System.out.println(elements.size());

        for (int i=0; i<elements.size();i++){
            System.out.println("ücretler " + i + ": " + elements.get(i).getText());

        }
        Thread.sleep(500);

        return elements;
    }

    public void waitForXpath (String Xpath) throws InterruptedException {

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Xpath)));
        Thread.sleep(500);
    }

    public void clickByXpath(String Xpath) throws InterruptedException {

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Xpath)));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(Xpath)));
        Thread.sleep(500);
        driver.findElement(By.xpath(Xpath)).click();
    }

    public void clickByXpath(String Xpath, int i) throws InterruptedException {

        //wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Xpath)));
        //wait.until(ExpectedConditions.elementToBeClickable(By.xpath(Xpath)));
        Thread.sleep(500);
        driver.findElements(By.xpath(Xpath)).get(i).click();
    }

    public void clickByClassName(String ClassName) throws InterruptedException {

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(ClassName)));
        wait.until(ExpectedConditions.elementToBeClickable(By.className(ClassName)));
        Thread.sleep(500);
        driver.findElement(By.className(ClassName)).click();
    }

    public void writeByXpath(String Xpath, String text) throws InterruptedException {

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Xpath)));
        driver.findElement(By.xpath(Xpath)).clear();
        Thread.sleep(500);
        driver.findElement(By.xpath(Xpath)).sendKeys(new CharSequence[]{text});
        driver.findElement(By.xpath(Xpath)).sendKeys(new CharSequence[]{Keys.TAB});
    }

    public void writeByClassName(String ClassName, String text) throws InterruptedException {

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ClassName)));
        driver.findElement(By.xpath(ClassName)).clear();
        Thread.sleep(500);
        driver.findElement(By.xpath(ClassName)).sendKeys(new CharSequence[]{text});
        driver.findElement(By.xpath(ClassName)).sendKeys(new CharSequence[]{Keys.TAB});
    }

    private void textControlByXpath(String Xpath, String text) throws InterruptedException{

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Xpath)));
        Thread.sleep(500);
        System.out.println(driver.findElement(By.xpath(Xpath)).getText());
        Assert.assertEquals(driver.findElement(By.xpath(Xpath)).getText(), text);
    }

    private void textNotEquelControlByXpath(String Xpath, String text) throws InterruptedException{

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Xpath)));
        Thread.sleep(500);
        System.out.println(driver.findElement(By.xpath(Xpath)).getText());
        Assert.assertNotEquals(driver.findElement(By.xpath(Xpath)).getText(), text);
    }

    private String getTextFromAttributeByXpath(String Xpath, String attribute, String firstPartString, String lastPartString) throws InterruptedException{

        //wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Xpath)));
        Thread.sleep(500);
        String s = driver.findElements(By.xpath(Xpath)).get(0).getAttribute(attribute);
        s = s.substring(s.indexOf(firstPartString) + firstPartString.length(),s.indexOf(lastPartString));
        System.out.println(s);
        return s;
    }
}

