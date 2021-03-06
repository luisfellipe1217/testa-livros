// Generated by Selenium IDE
package com.luisfellipe.testalivro.SuiteTest;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
//import static org.junit.Assert.*;
//import static org.hamcrest.CoreMatchers.is;
//import static org.hamcrest.core.IsNot.not;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.remote.RemoteWebDriver;
//import org.openqa.selenium.remote.DesiredCapabilities;
//import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
//import org.openqa.selenium.Alert;
//import org.openqa.selenium.Keys;
import java.util.*;
//import java.net.MalformedURLException;
//import java.net.URL;
//import java.time.Duration;
import java.util.concurrent.TimeUnit;
//import junit.framework.Assert;
import static junit.framework.Assert.assertEquals;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.Keys;
//import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SuiteTest {

    private WebDriver driver;
    private Map<String, Object> vars;
    private FluentWait<WebDriver> wait;
    JavascriptExecutor js;
    private final String pathChrome = "C:\\Selenium\\chromedriver.exe"; //Caminho do driver chrome
    private String isbn13Sub;
    String authorSub;
    String authorAme;
    String authorAmz;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", pathChrome);
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        js = (JavascriptExecutor) driver;
    }

    @Test
    public void Teste() throws InterruptedException {
        // CT01Submarino
        // abre o submarino
        driver.get("https://www.submarino.com.br/");
        //abrir a categoria livros        
        {
            WebElement element = driver.findElement(By.cssSelector(".mmn-sdb-title"));
            Actions builder = new Actions(driver);
            builder.moveToElement(element).perform();
        }
        // abrir menu de lojas
        driver.findElement(By.linkText("Vem ver as lojasAbrir menu")).click();
        // abrir categoria livros
        driver.findElement(By.cssSelector(".szr-13 > .sz-1 > .mmn-itm-link")).click();
        //rola a pagina          
        js.executeScript("window.scrollTo(0,3378)");
        // seleciona a primeiro livro  

        driver.findElement(By.xpath("//*[@id=\"content-bottom\"]/div[3]/div/div/section/div/div/div/div[2]/div/div/div/div/div/div[1]/div/div/div/div[2]/a/section")).click();
        //rola a pagina         
        js.executeScript("window.scrollTo(0,4350)");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            System.out.println(e);
        }

        //salva o autor do livro na variavel desejada       
        WebElement Author = driver.findElement(By.xpath("//*[@id=\"info-section\"]/div[2]/section/div[2]/section/table/tbody/tr[13]/td[2]"));
        authorSub = Author.getText().toUpperCase();
        System.out.println("nome do autor no submarino:" + authorSub);

        //salva isbn13 na variavel desejada         
        WebElement Isbn = driver.findElement((By.xpath("//*[@id=\"info-section\"]/div[2]/section/div[2]/section/table/tbody/tr[12]/td[2]")));
        isbn13Sub = Isbn.getText();
        System.out.println("ISBN:" + isbn13Sub);

        // CT02Americanas
        driver.get("https://www.americanas.com.br/");

        //Pesquisar pelo código isbn
        driver.findElement(By.id("h_search-input")).click();
        driver.findElement(By.id("h_search-input")).sendKeys(isbn13Sub);
        driver.findElement(By.id("h_search-input")).sendKeys(Keys.ENTER);

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            System.out.println(e);
        }
        
        js.executeScript("window.scrollTo(0,550)");

        //clicar no primeiro resultado:
        driver.findElement(By.xpath("/html/body/div/div/div/div[3]/div[2]/div[1]/div/a/div[2]")).click();

        js.executeScript("window.scrollTo(0,1550)");
        // salvando o author na variavel desejada
        WebElement author2 = driver.findElement(By.cssSelector(".src__View-wbypax-3:nth-child(3) > .src__Text-wbypax-4:nth-child(2)"));
        authorAme = author2.getText().toUpperCase();
        System.out.println("Autor Americanas:" + authorAme);

        // CT03Amazon        
        // Abre site amazon 
        driver.get("https://www.amazon.com.br/");
        //pesquisar por isbn
        driver.findElement(By.id("twotabsearchtextbox")).sendKeys(isbn13Sub);
        driver.findElement(By.cssSelector("#nav-search-submit-text > .nav-input")).click();
        // selecionar primeiro resultado
        driver.findElement(By.cssSelector(".s-image")).click();
        // salvar autor
        WebElement author3 = driver.findElement(By.xpath("//*[@id=\"bylineInfo\"]/span[1]/a"));
        authorAmz = author3.getText().toUpperCase();
        System.out.println("Autor Amazon: " + authorAmz);
        
        assertEquals(authorSub, authorAme, authorAmz);        
    }

    @After
    public void tearDown() {
        driver.quit();
    }

}
