package it.pn.frontend.e2e.stepDefinitions.mittente;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import it.pn.frontend.e2e.listeners.Hooks;

import it.pn.frontend.e2e.pages.destinatario.DestinatarioPage;

import it.pn.frontend.e2e.utility.DataPopulation;
import lombok.extern.slf4j.Slf4j;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;


import java.util.Map;
import java.util.UUID;

@Slf4j
public class NewNotifichePagoPATest {
    private final WebDriver driver = Hooks.driver;
    private final String FILE_TOKEN_LOGIN = "tokenLogin.yaml";

    DestinatarioPage destinatarioPage = new DestinatarioPage(driver);

    @Then("Creo in background una notifica per destinatario tramite API REST")
    public void creoUnaNotificaPerDestinatarioTramiteAPIREST(){
        destinatarioPage.checkCreateNewNotification();
    }

    @And("Si aggiunge un destinatario alla notifica")
    public void siAggiungeUnDestinatarioAllaNotifica(Map<String, String> datiDestinatario) {
        destinatarioPage.aggiuntaDestinatarioANotifica(datiDestinatario);
    }

    @When("Si inizializzano i dati per la notifica")
    public void siInizializzanoIDatiPerLaNotifica(Map<String, String> datiNotifica) {
        destinatarioPage.inizializzazioneDatiNotifica(datiNotifica);
    }

    @And("Si completa percorso RADD")
    public void siCompletaPercorsoRADD(Map<String,String> datiDestinatario) {
        DataPopulation dataPopulation = new DataPopulation();
        String environment = System.getProperty("environment");
        String token = "";
        switch (environment) {
            case "dev" -> {
                /* TODO */
            }
            case "test" ->
                    token = dataPopulation.readDataPopulation(FILE_TOKEN_LOGIN).get("tokentestRaddista1").toString();
            default -> {
                log.error("Ambiente non valido");
                Assert.fail("Ambiente non valido o non trovato!");
            }
        }
        String operationId = UUID.randomUUID().toString();
        destinatarioPage.raddFlow(token,datiDestinatario.get("tipoDestinatario"), datiDestinatario.get("codiceFiscale"),operationId);
    }
}