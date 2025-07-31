package it.pn.frontend.e2e.stepDefinitions.destinatario.personaGiuridica;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import it.pn.frontend.e2e.common.BasePage;
import it.pn.frontend.e2e.config.DataPopulationConfig;
import it.pn.frontend.e2e.config.WebDriverConfig;
import it.pn.frontend.e2e.section.destinatario.personaGiuridica.RegistraChiavePubblicaPGSection;
import it.pn.frontend.e2e.utility.WebTool;
import jakarta.annotation.PostConstruct;
import org.junit.jupiter.api.Assertions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.random.RandomGenerator;
import java.util.Map;

public class RegistraChiavePubblicaPGPagoPATest extends BasePage {

    private final Logger logger = LoggerFactory.getLogger(RegistraChiavePubblicaPGPagoPATest.class);
    private RegistraChiavePubblicaPGSection registraChiavePubblicaPGSection;

    @Autowired
    private WebDriverConfig webDriverConfig;

    @Autowired
    private DataPopulationConfig dataPopulationConfig;

    private WebTool webTool;

    @PostConstruct
    public void init(){
        logger.info("INIT TEST...: ");
        webTool = new WebTool(driver);
        registraChiavePubblicaPGSection = new RegistraChiavePubblicaPGSection(driver);
    }

    @And("Nella sezione Registra chiave pubblica si inseriscono i dati della chiave pubblica")
    public void nellaSezioneRegistraUnaChiavePubblicaInserireIDati(Map<String, String> chiave) {
        logger.info("Nella sezione Registra chiave pubblica inserire i dati");
        String nome = chiave.get("nome");
        registraChiavePubblicaPGSection.insertNome(nome);
        registraChiavePubblicaPGSection.insertPublicKey();
    }

    @And("Cliccare su registra")
    public void cliccareSuRegistra() {
        logger.info("Cliccare sul bottone registra");
        registraChiavePubblicaPGSection.selezionareRegistraButton();
    }

    @And("Si visualizza correttamente la sezione Ottieni Parametri")
    public void siVisualizzaCorrettamenteLaSezioneOttieniParametri() {
        registraChiavePubblicaPGSection.waitLoadOttieniParametriSection();
    }

    @And("Su Ottieni Parametri si copia correttamente il campo KID cliccando sul bottone di copia")
    public void ottieniParametriSiCopiaCorrettamenteIlCampoKIDCliccandoSulBottoneDiCopia() {
        logger.info("Si clicca copia sul tasto 'KID' per copiare il campo Kid");
        String copiedValue = registraChiavePubblicaPGSection.ottieniParametriCopiaKIDRegistraChiavePubblica();
        logger.info("Il campo KID copiato è: {}", copiedValue);
    }

    @And("Su Ottieni Parametri si copia correttamente il campo Issuer cliccando sul bottone di copia")
    public void ottieniParametriSiCopiaCorrettamenteIlCampoIssuerCliccandoSulBottoneDiCopia() {
        logger.info("Si clicca copia sul tasto 'Issuer' per copiare il campo Issuer");
        String copiedValue = registraChiavePubblicaPGSection.ottieniParametriCopiaIssuerRegistraChiavePubblica();
        logger.info("Il campo Issuer copiato è: {}", copiedValue);
    }

    @And("Nella sezione Registra chiave pubblica si inseriscono i dati della chiave pubblica con numero di caratteri superiori")
    public void nellaSezioneRegistraChiavePubblicaSiInserisconoIDatiDellaChiavePubblicaConNumeroDiCaratteriSuperiori() {
        logger.info("Nella sezione Registra chiave pubblica inserire un numero di caratteri maggiori 254 per il nome 500 per la publicKey");
        registraChiavePubblicaPGSection.insertNome(generateRandomString(260));
        registraChiavePubblicaPGSection.insertPublicKey(generateRandomString(501));
    }
    public  String generateRandomString(int length) {
        final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        final RandomGenerator randomGenerator = RandomGenerator.getDefault();
        return randomGenerator.ints(length, 0, CHARACTERS.length())
                .mapToObj(CHARACTERS::charAt)
                .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
                .toString();
    }

    @And("Verifica messaggio Nome di errore {string}")
    public void verificaMessaggioNomeDiErrore(String testo) {
        registraChiavePubblicaPGSection.verificaMessaggioNomeDiErrore(testo);
    }

    @And("Verifica messaggio PublicKey di errore {string}")
    public void verificaMessaggioPublicKeyDiErrore(String testo) {
        registraChiavePubblicaPGSection.verificaMessaggioPublicKeyDiErrore(testo);
    }

    @And("Verifica tasto registra disabilitato")
    public void verificaTastoRegistraDisabilitato() {
        webTool.waitTime(1);
        registraChiavePubblicaPGSection.verificaTastoRegistraDisabilitato();
    }

    @And("Cliccare sui tre puntini con stato {string}")
    public void cliccareSuiTrePuntiniConStato(String testo) {
        registraChiavePubblicaPGSection.cliccareSuiTrePuntiniPublicKeyConStato(testo);
    }

    @And("Verifica stato {string}")
    public void verificaStato(String stato) {
        registraChiavePubblicaPGSection.verificaStato(stato);
    }

    @Then("Verifica stato Chiave Personale {string}")
    public void verificaStatoChiavePersonale(String stato) {
        Assertions.assertTrue(
                registraChiavePubblicaPGSection.verificaStatoChiavePersonale(stato),
                "Non presente lo stato: " + stato + " inerente a una Chiave Personale"
        );
    }

    @And("Cliccare sui tre puntini Virtual key con stato {string}")
    public void cliccareSuiTrePuntiniVirtualKeyConStato(String stato) {
        registraChiavePubblicaPGSection.cliccareSuiTrePuntiniVirtualKeyConStato(stato);
    }

    @And("Verifica Assenza stato Chiave Personale {string}")
    public void verificaAssenzaStatoChiavePersonale(String stato) {
        logger.info(" registraChiavePubblicaPGSection.verificaStatoChiavePersonale(stato): {}", registraChiavePubblicaPGSection.verificaStatoChiavePersonale(stato));
        Assertions.assertFalse(
                registraChiavePubblicaPGSection.verificaStatoChiavePersonale(stato),
                "Errore presente lo stato: " + stato + " inerente a una Chiave Personale"
        );
    }

    @Then("Verifica Assenza Tre Puntini con stato {string}")
    public void verificaAssenzaTrePuntiniConStato(String stato) {
        logger.info("Verifica Assenza Tre Puntini con stato");
        Assertions.assertFalse(
                registraChiavePubblicaPGSection.verificaStatoChiavePersonale(stato),
                "Errore Presenza Opzione Mostra di piu con stato: " + stato
        );
    }
}
