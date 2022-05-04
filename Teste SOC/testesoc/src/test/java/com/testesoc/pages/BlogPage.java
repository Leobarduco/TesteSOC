package com.testesoc.pages;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BlogPage {

    private static final String URL_BLOG = "https://www.soc.com.br/blog-de-sst/";
    private WebDriver browser;
    
    public BlogPage() {
        System.setProperty("webdriver.gecko.driver", "drivers/geckodriver.exe");
        this.browser = new FirefoxDriver();
        browser.navigate().to(URL_BLOG);
    } 

    public void fechar() {this.browser.quit();}

    public void preencherCampoDeBusca(String string) {
        browser.findElement(By.xpath("/html/body/div[2]/div/div/section[1]/div/div/div/div/div/section[2]/div/div/div[2]/div/div/div/div/form/div/input")).sendKeys("CIPA");
    }

    public void confirmaBusca() {
        browser.findElement(By.xpath("/html/body/div[2]/div/div/section[1]/div/div/div/div/div/section[2]/div/div/div[2]/div/div/div/div/form/div/button")).click();
    }

    public boolean confirmaURLPaginaDeBusca() {return browser.getCurrentUrl().equals(URL_BLOG);}

    public boolean confirmaResultadodaBusca() {return browser.getPageSource().contains("Search Results");}

    public void tiraScreenshot() throws IOException {
        File scrFile = ((TakesScreenshot)browser).getScreenshotAs(OutputType.FILE);
        Date data = new Date();
        FileUtils.copyFile(scrFile, new File("src/test/resources/evidencias/evidencia"+ data.getTime()+".png"));
    }

    public void reloadPaginaCasoHajaErro() {
        boolean existeElemento = false;
        browser.navigate().refresh();

        while(!existeElemento){             
            try {                         
                preencheFiltro(); 
                existeElemento = true;
            } catch (Exception e) {
                browser.navigate().refresh(); 
                existeElemento = false;
            }                     
        }      
    } 

    public void clicaMenuSuspenso() {
        browser.findElement(By.xpath("/html/body/div[1]/div/header[1]/div/div/div/div/div/section[2]/div/div/div/div/div/div[2]/div/nav[1]/ul/li[2]")).click();
    }

    public void selecionaOpcaoMenuSuspenso() {        
        browser.findElement(By.xpath("/html/body/div[1]/div/header[1]/div/div/div/div/div/section[2]/div/div/div/div/div/div[2]/div/nav[1]/ul/li[2]/ul/li[7]/a")).click();
    }

    public void clicaBotaoPesquisaCredenciado() {
        browser.findElement(By.xpath("/html/body/div[2]/div/div/section[1]/div/div/div/div/div/section/div/div/div/div/div/div[4]/div/div/a")).click();
    }

    public void preencheFiltro() {
        new WebDriverWait(browser, Duration.ofSeconds(3)).until(ExpectedConditions.elementToBeClickable(By.id("ipt-busca-credenciado-2")));
        browser.findElement(By.id("ipt-busca-credenciado-2")).sendKeys("Santos, SP, Brasil");
    }

    public void submeteBusca() {browser.findElement(By.id("botao-buscar")).click();}

    public void selecionaCredenciadoNaLista() {
        new WebDriverWait(browser, Duration.ofSeconds(5)).until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div/div/section[2]/div[3]/div[1]/div/div[1]/div/section/div[2]/p/span")));
        WebElement nomeEmpresa = browser.findElement(By.xpath("/html/body/div/div/section[2]/div[3]/div[1]/div/div[1]/div/section/div[2]/p/span"));
        ((JavascriptExecutor) browser).executeScript("arguments[0].click();", nomeEmpresa);
        new WebDriverWait(browser, Duration.ofSeconds(5)).until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div/div/section[2]/div[3]/div[2]/div/div/div/div/div[2]/div[2]/div/div[3]/div/div/div[29]/a/button")));
        browser.findElement(By.xpath("/html/body/div/div/section[2]/div[3]/div[2]/div/div/div/div/div[2]/div[2]/div/div[3]/div/div/div[29]/a/button")).click();
    }

    public boolean confirmaURLPaginaPerfilCredenciado() {return browser.getCurrentUrl().equals(URL_BLOG);}

    public boolean confirmaPerfilExibidoNaPagina() {return browser.getPageSource().contains("Buscar prestadores");}
}
