package com.testesoc.steps;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.testesoc.pages.BlogPage;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;

public class BuscaBlogSteps {

    private BlogPage blogPage;
    private static ExtentHtmlReporter htmlReporter;
    private static ExtentReports extentReport;
    private static ExtentTest extentTest;

    @Before
    public void beforeCenario(Scenario cenario) {
        if(extentReport == null) {
            extentReport = new ExtentReports();
            htmlReporter = new ExtentHtmlReporter("src/test/resources/htmlReporter.html");
            extentReport.attachReporter(htmlReporter);
        }
        extentTest = extentReport.createTest(cenario.getId());
    }

    @After
    public void afterCenario(Scenario cenario) {
        extentTest.log(Status.PASS, "Cenario "+ cenario.getName()+ " executado com sucesso!");
        extentReport.flush();
    }

    @Dado("usuario acessa o blog")
    public void usuario_acessa_o_blog() {this.blogPage = new BlogPage();}

    @Quando("realiza uma pesquisa no site")
    public void realiza_uma_pesquisa_no_site() {
        blogPage.preencherCampoDeBusca("CIPA");
        blogPage.confirmaBusca();
    }

    @Entao("eh redirecionado para pagina com os resultados da pesquisa")
    public void eh_redirecionado_para_pagina_com_os_resultados_da_pesquisa() throws IOException {
        assertFalse(blogPage.confirmaURLPaginaDeBusca());
        assertTrue(blogPage.confirmaResultadodaBusca());
        blogPage.tiraScreenshot();
        blogPage.fechar();
    }

    @Dado("usuario buscando credenciado no site")
    public void usuario_buscando_credenciado_no_site() {this.blogPage = new BlogPage();}

    @E("acessando o menu funcionalidades")
    public void acessando_o_menu_funcionalidades() {blogPage.clicaMenuSuspenso();}

    @E("acessando o submenu redesocnet")
    public void acessando_o_submenu_redesocnet() {blogPage.selecionaOpcaoMenuSuspenso();}

    @E("clicando em buscar credenciados")
    public void clicando_em_buscar_credenciados() {blogPage.clicaBotaoPesquisaCredenciado();}
        
    @Quando("filtra por localizacao")
    public void filtra_por_localizacao() {
        try {          
            blogPage.preencheFiltro();               
        } catch (Exception e) {
            blogPage.reloadPaginaCasoHajaErro();
        } finally {
            blogPage.submeteBusca();
        }
    }

    @Entao("exibe o perfil do credenciado")
    public void exibe_o_perfil_do_credenciado() throws IOException {
        blogPage.selecionaCredenciadoNaLista();
        assertFalse(blogPage.confirmaURLPaginaPerfilCredenciado());
        assertTrue(blogPage.confirmaPerfilExibidoNaPagina());
        blogPage.tiraScreenshot();
        blogPage.fechar();
    }
}
