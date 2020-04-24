# Hepsiburada.com Test Automation Cases

Hepsiburada.com sistemine Selenium altyapısını kullanarak webdriver ile login, arama, sepete ekleme gibi fonksiyonların testlerinin otomasyonunun sağlanması.

## Başlangıç

Bu doküman ile projenin bir kopyasını yerel makinenizde çalıştırılmasını sağlayabileceksiniz.

### Ön şartlar

Programı kurmak için aşağıdaki programlara ihtiyaç vardır.

* Java v 1.8
* Maven v 3.6.1
* Java IDE

### Kurulum

Projenin klonunu makinanıza kurabilirsiniz.

https://help.github.com/en/desktop/contributing-to-projects/cloning-a-repository-from-github-to-github-desktop

Yada gitHub linki ile direk kurulum yapabilirsiniz.

ÖNENLİ NOT: Kurulum yapıldıktan sonra her 2 test case içindeki chromedriver lokasyon bilgileri mevcut makinadaki chromedriver path ile güncellenmelidir.

```bash
System.setProperty("webdriver.chrome.driver","C:\\Users\\cahit.evmek\\Desktop\\HepsiBuradaCases\\Driver\\chromedriver.exe");
```

```bash
capabilities.setCapability("chrome.binary","C:\\Users\\cahit.evmek\\Desktop\\SelleniumProject\\src\\library\\drivers\\chromedriver.exe");
```

### Programlama Hakkında Notlar

* Proglama esnasında write, click gibi genel işlemler function halinde kullanılmıştır. 
* Genel fonksiyonlar genel bir class altında toplanacaktır (ör: MainUtilities.java gibi). 


## Yazar

* **Cahit Evmek** - [HepsiBuradaCases](https://github.com/cahitevmek/HepsiBuradaCases)


