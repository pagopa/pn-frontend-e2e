package it.pn.frontend.e2e.stepDefinitions.destinatario.personaGiuridica;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import it.pn.frontend.e2e.common.BasePage;
import it.pn.frontend.e2e.config.DataPopulationConfig;
import it.pn.frontend.e2e.config.WebDriverConfig;
import it.pn.frontend.e2e.config.WebDriverManager;
import it.pn.frontend.e2e.pages.destinatario.personaGiuridica.IntegrazioneAPIPGPage;
import it.pn.frontend.e2e.section.destinatario.personaGiuridica.RegistraChiavePubblicaPGSection;
import it.pn.frontend.e2e.utility.WebTool;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.Setter;
import org.junit.jupiter.api.Assertions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

public class IntegrazioneAPIPGPagoPaTest extends BasePage {

    private final Logger logger = LoggerFactory.getLogger("NotifichePGPagoPATest");
    Map<String, Object> personaGiuridica = new HashMap<>();

    private IntegrazioneAPIPGPage integrazioneAPIPGPage;

    @Autowired
    private WebDriverConfig webDriverConfig;

    @Autowired
    private DataPopulationConfig dataPopulationConfig;
    private WebDriverManager webDriverManager;

    private WebTool webTool;

    @Setter
    @Getter
    private String publicKey;

    private RegistraChiavePubblicaPGSection registraChiavePubblicaPGSection;

    @PostConstruct
    public void init(){
        logger.info("INIT TEST...: ");
        webTool = new WebTool(driver);
        integrazioneAPIPGPage = new IntegrazioneAPIPGPage(driver);
        registraChiavePubblicaPGSection = new RegistraChiavePubblicaPGSection(driver);
    }

    @And("Si controlla la comparsa del label di stato 'Attiva' e del pop up di conferma per la creazione della chiave pubblica")
    public void checkConfermaCreazioneChiavePubblica() {
        logger.info("Si controlla la comparsa del label di stato 'Attiva' e del pop up di conferma per la creazione della chiave pubblica");
        integrazioneAPIPGPage.checkConfermaCreazioneChiavePubblica();
    }

    @And("C'è almeno una chiave pubblica censita nella tabella delle chiavi pubbliche sulla pagina Integrazione API")
    public void verificaPresenzaChiavePubblicheCensite() {
        logger.info("C'è almeno una chiave pubblica censita nella tabella delle chiavi pubbliche sulla pagina Integrazione API");
        integrazioneAPIPGPage.verificaPresenzaChiavePubblicheCensite();
    }

    @Then("Si verifica che la tabella delle chiavi pubbliche sia presente")
    public void verificaTabellaChiavePubbliche() {
        logger.info("Si verifica che la tabella delle chiavi pubbliche sia presente");
        integrazioneAPIPGPage.verificaTabellaChiaviPubbliche();
    }

    @And("Da Visualizza codice si copia correttamente il campo Chiave Personale cliccando sul bottone di copia")
    public void visualizzaCodicesiCopiaCorrettamenteIlCampoChiavePersonaleCliccandoSulBottoneDiCopia() {
        logger.info("Si clicca copia sul tasto 'Chiave Personale' per copiare il campo Chiave Personale");
        String copiedValue = integrazioneAPIPGPage.visualizzaCodiceCopiaChiavePersonale();
        logger.info("Il campo Chiave Personale copiato è: {}", copiedValue);
    }

    @And("Nella pagina Integrazione API si controlla sia presente il bottone Genera chiave personale")
    public void nellaPaginaIntegrazioneAPISiControllaSiaPresenteIlBottoneGeneraChiavePersonale() {
            Assertions.assertTrue(
                    integrazioneAPIPGPage.nellaPaginaIntegrazioneAPISiControllaSiaPresenteIlBottoneGeneraChiavePersonale(),
                    "Il bottone Genera chiave personale non è trovato");
    }
    @And("Da Visualizza codice si copia correttamente il campo KID cliccando sul bottone di copia")
    public void visualizzaCodiceSiCopiaCorrettamenteIlCampoKIDCliccandoSulBottoneDiCopia() {
        logger.info("Si clicca copia sul tasto 'KID' per copiare il campo KID");
        String copiedValue = integrazioneAPIPGPage.visualizzaCodiceCopiaKID();
        logger.info("Il campo KID copiato è: {}", copiedValue);
    }

    @And("Da Visualizza codice si copia correttamente il campo Issuer cliccando sul bottone di copia")
    public void visualizzaCodiceSiCopiaCorrettamenteIlCampoIssuerCliccandoSulBottoneDiCopia() {
        logger.info("Si clicca copia sul tasto 'Issuer' per copiare il campo Issuer");
        String copiedValue = integrazioneAPIPGPage.visualizzaCodiceCopiaIssuer();
        logger.info("Il campo Issuer copiato è: {}", copiedValue);
    }

    @And("Si copia il valore della chiave pubblica dalla tabella delle chiavi pubbliche")
    public void copiaValorePublicKeyDaTabellaChiavePubbliche () {
        logger.info("Si copia il valore della chiave pubblica dalla tabella delle chiavi pubbliche");
        String copiedValue = integrazioneAPIPGPage.tabellaChiaviPubblicheCopiaValorePublicKey();
        logger.info("Il campo Valore della chiave pubblica copiata è: {}", copiedValue);
        setPublicKey(copiedValue);
    }

    @And("Nella sezione Registra chiave pubblica si inseriscono i dati della chiave pubblica con public key della chiave precedentemente copiata dalla tabella delle chiavi pubbliche")
    public void nellaSezioneRegistraUnaChiavePubblicaInserireIDatiConValorePublicKeyCopiata(Map<String, String> chiave) {
        logger.info("Nella sezione Registra chiave pubblica inserire i dati con valore della public key precedentemente copiata");
        registraChiavePubblicaPGSection.waitLoadRegistraChiavePubblicaPGPage();
        String nome = chiave.get("nome");
        registraChiavePubblicaPGSection.insertNome(nome);
        registraChiavePubblicaPGSection.insertPublicKey(getPublicKey());
    }
}
