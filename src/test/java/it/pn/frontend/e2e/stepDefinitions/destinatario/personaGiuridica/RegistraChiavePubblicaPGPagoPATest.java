package it.pn.frontend.e2e.stepDefinitions.destinatario.personaGiuridica;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import it.pn.frontend.e2e.common.BasePage;
import it.pn.frontend.e2e.config.DataPopulationConfig;
import it.pn.frontend.e2e.config.WebDriverConfig;
import it.pn.frontend.e2e.section.destinatario.personaGiuridica.RegistraChiavePubblicaPGSection;
import it.pn.frontend.e2e.utility.WebTool;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.MessageFormat;
import java.util.Map;

public class RegistraChiavePubblicaPGPagoPATest extends BasePage {

    private final Logger logger = LoggerFactory.getLogger("NotifichePGPagoPATest");
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
        String publicKey = chiave.get("publicKey");

        registraChiavePubblicaPGSection.insertNome(nome);
        registraChiavePubblicaPGSection.insertPublicKey(publicKey);
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

    @And("Si copia correttamente il campo KID cliccando sul bottone di copia")
    public void siCopiaCorrettamenteIlCampoKIDCliccandoSulBottoneDiCopia() {
        logger.info("Si clicca copia sul tasto 'KID' per copiare il campo Kid");
        String copiedValue = registraChiavePubblicaPGSection.copiaKIDRegistraChiavePubblica();
        logger.info("Il campo KID copiato è: {}", copiedValue);
    }

    @And("Si copia correttamente il campo Issuer cliccando sul bottone di copia")
    public void siCopiaCorrettamenteIlCampoIssuerCliccandoSulBottoneDiCopia() {
        logger.info("Si clicca copia sul tasto 'Issuer' per copiare il campo Issuer");
        String copiedValue = registraChiavePubblicaPGSection.copiaIssuerRegistraChiavePubblica();
        logger.info("Il campo Issuer copiato è: {}", copiedValue);
    }

}
