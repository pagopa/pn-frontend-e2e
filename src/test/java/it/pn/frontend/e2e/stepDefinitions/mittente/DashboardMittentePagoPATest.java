package it.pn.frontend.e2e.stepDefinitions.mittente;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import it.pn.frontend.e2e.listeners.Hooks;
import it.pn.frontend.e2e.pages.mittente.DashboardPage;
import it.pn.frontend.e2e.pages.mittente.PiattaformaNotifichePage;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;


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
        Assert.assertTrue("il bottone Filtra è disabilitato", piattaformaNotifichePage.verificaBottoneFiltraDisabilitato());
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
