package it.pn.frontend.e2e.stepDefinitions.mittente;

import com.google.common.base.CharMatcher;
import com.google.common.base.Splitter;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import it.pn.frontend.e2e.api.mittente.AccettazioneRichiestaNotifica;
import it.pn.frontend.e2e.listeners.Hooks;
import it.pn.frontend.e2e.listeners.NetWorkInfo;
import it.pn.frontend.e2e.model.enums.AppPortal;
import it.pn.frontend.e2e.model.singleton.NotificationSingleton;
import it.pn.frontend.e2e.pages.mittente.AreaRiservataPAPage;
import it.pn.frontend.e2e.pages.mittente.DashboardPage;
import it.pn.frontend.e2e.pages.mittente.InvioNotifichePAPage;
import it.pn.frontend.e2e.pages.mittente.PiattaformaNotifichePage;
import it.pn.frontend.e2e.section.CookiesSection;
import it.pn.frontend.e2e.section.mittente.*;
import it.pn.frontend.e2e.stepDefinitions.common.BackgroundTest;
import it.pn.frontend.e2e.stepDefinitions.destinatario.personaFisica.LoginPersonaFisicaPagoPA;
import it.pn.frontend.e2e.stepDefinitions.destinatario.personaGiuridica.LoginPGPagoPATest;
import it.pn.frontend.e2e.utility.CookieConfig;
import it.pn.frontend.e2e.utility.DataPopulation;
import it.pn.frontend.e2e.utility.WebTool;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

import static org.apache.commons.lang3.StringUtils.isNumeric;
import static org.apache.commons.lang3.StringUtils.substring;

public class DashboardMittentePagoPATest {

    private static final Logger logger = LoggerFactory.getLogger("DashboardMittentePagoPATest");
    private final WebDriver driver = Hooks.driver;
    DashboardPage dashboardPage = new DashboardPage(this.driver);
    PiattaformaNotifichePage piattaformaNotifichePage = new PiattaformaNotifichePage(this.driver);


    @When("Nella pagina Piattaforma Notifiche cliccare sul bottone Statistiche")
    public void clickSezioneStatistiche() {
        dashboardPage.clickStatistiche();
    }

    @Then("Si visualizza correttamente la pagina di Statistiche")
    public void siVisualizzaCorrettamenteLaPaginaStatistiche() {
        dashboardPage.waitLoadDashboardPAPage();
    }

    @And("Nella pagina Statistiche cliccare sul bottone ultimi 6 mesi")
    public void nellaPaginaStatisticheCliccareSulBottoneUltimi6Mesi() {
        dashboardPage.clickUltimi6Mesi();
    }

    @And("Nella pagina Statistiche Notifiche digitali cliccare sul bottone ultimi 6 mesi")
    public void nellaPaginaStatisticheNotificheDigitaliCliccareSulBottoneUltimi6Mesi() {
        dashboardPage.clickUltimi6MesiNotificheDigitali();
    }

    @And("Nella pagina Statistiche si visualizza il grafico")
    public void siVisualizzaIlGrafico() {
        dashboardPage.checkVisualizzaGrafico();
    }

    @And("Nella pagina Statistiche si clicca il bottone esporta jpeg")
    public void siCliccaEsportaJpeg() throws AWTException {
        dashboardPage.clickJpegExport();
    }

    @And("Si elimina jpeg scaricato")
    public void siEliminaJpegScaricato() {
        dashboardPage.eliminaJpeg();
    }

    @And("Nella pagina Statistiche si visualizza il grafico Invii digitali per esito")
    public void siVisualizzaIlGraficoInviiDigitaliPerEsito() {
        dashboardPage.checkVisualizzaGraficoInviiDigitaliPerEsito();
    }

    @And("Nella pagina Statistiche si visualizza il grafico Tempo medio degli invii digitali")
    public void siVisualizzaIlGraficoTempoMedioInviiDigitali() {
        dashboardPage.checkVisualizzaGraficoTempoMedioInviiDigitali();
    }

    @And("Nella pagina Statistiche si visualizza il grafico Errori tecnici per tipologia")
    public void siVisualizzaIlGraficoErroriTecnici() {
        dashboardPage.checkVisualizzaGraficoErroriTecnici();
    }

    @And("Nella pagina Statistiche si visualizza il grafico Notifiche inviate per stato")
    public void siVisualizzaIlGraficoNotificheInviatePerStato() {
        dashboardPage.checkVisualizzaGraficoInviatePerStato();
    }

    @And("Nella pagina Statistiche si visualizza il grafico Notifiche consegnate per modalità di invio")
    public void siVisualizzaIlGraficoNotificheConsegnatePerModalitoDiInvio() {
        dashboardPage.checkVisualizzaGraficoConsegnatePerModalitaInvio();
    }

    @And("Nella pagina Statistiche si inserisce una data errata")
    public void nellaPaginaStatisticheSiInserisceUnaDataErrata() {
        dashboardPage.insertDataErrata();
    }

    @And("Nella pagina Statistiche Notifiche digitali si inserisce una data errata")
    public void nellaPaginaStatisticheNotificheDigitaliSiInserisceUnaDataErrata() {
        dashboardPage.insertDataErrataNotificheDigitali();
    }

    @And("Nella pagina Statistiche il bottone Filtra disabilitata")
    public void nellaPaginaStatisticheIlBottoneFiltraDisabilitata() {
        Assertions.assertTrue(piattaformaNotifichePage.verificaBottoneFiltraDisabilitato(),"il bottone Filtra è disabilitato");
    }

    @And("Nella pagina Statistiche si clicca sul bottone Annulla filtri")
    public void nellaPaginaStatisticheSiCliccaSulBottoneAnnullaFiltri() {
        dashboardPage.clickAnnullaFiltriButton();
    }

    @And("Nella pagina Statistiche Notifiche digitali si clicca sul bottone Annulla filtri")
    public void nellaPaginaStatisticheNotificheDigitaliSiCliccaSulBottoneAnnullaFiltri() {
        dashboardPage.clickAnnullaFiltriButtonNotificheDigitali();
    }

    @And("Nella pagina Statistiche si clicca sul bottone Filtra")
    public void nellaPaginaStatisticheSiCliccaSulBottoneFiltra() {
        dashboardPage.clickFiltraButton();
    }

    @And("Nella pagina Statistiche Notifiche digitali si clicca sul bottone Filtra")
    public void nellaPaginaStatisticheNotifichDigitaliSiCliccaSulBottoneFiltra() {
        dashboardPage.clickFiltraButtonNotificheDigitali();
    }

    @And("Nella pagina Statistiche si inserisce una data corretta")
    public void nellaPaginaStatisticheSiInserisceUnaDataCorretta() {
        dashboardPage.insertDataCorretta();
    }

    @And("Nella pagina Statistiche Notifiche digitali si inserisce una data corretta")
    public void nellaPaginaStatisticheNotificheDigitaliSiInserisceUnaDataCorretta() {
        dashboardPage.insertDataCorrettaNotificheDigitali();
    }

    @And("Si verifica che tipo di grafico è {string}")
    public void siVerificaCheTipoDiGraficoE(String tipoGrafico) {
        dashboardPage.checkTipoGrafico(tipoGrafico);
    }

    @And("Si verifica che tipo di grafico per modalita di invio è {string}")
    public void siVerificaCheTipoDiGraficoPerModalitaInvio(String tipoGrafico) {
        dashboardPage.checkTipoGraficoPerModalitaInvio(tipoGrafico);
    }

    @And("Si cambia tipo di grafico")
    public void siCambiaTipoDiGrafico() {
        dashboardPage.cambiaTipoGrafico();
    }

    @And("Si cambia tipo di grafico per modalita di invio")
    public void siCambiaTipoDiGraficoPerModalitaInvio() {
        dashboardPage.cambiaTipoGraficoPerModalitaInvio();
    }

    @And("Si sceglie opzione Settimane")
    public void siSceglieOpzioneSettimane() {
        dashboardPage.sceglieOpzioneSettimane();
    }

    @And("Si sceglie opzione Giorni")
    public void siSceglieOpzioneGiorni() {
        dashboardPage.sceglieOpzioneGiorni();
    }


}
