package it.pn.frontend.e2e.config;

import it.pn.frontend.e2e.common.DataPopulationValue;
import it.pn.frontend.e2e.model.delegate.DelegatePF;
import it.pn.frontend.e2e.model.delegate.DelegatePG;
import it.pn.frontend.e2e.model.delegate.DelegateRequestPF;
import it.pn.frontend.e2e.model.delegate.DelegateRequestPG;
import it.pn.frontend.e2e.model.recipients.Mittente;
import it.pn.frontend.e2e.model.recipients.DatiNotificaPg;
import it.pn.frontend.e2e.model.recipients.PersonaFisica;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Properties;

@Getter
@Configuration
@PropertySource( value = "file:config/data-population.properties", ignoreResourceNotFound = true )
public class DataPopulationConfig {

    //DATI DELEGATO PF
    private String displayName = DataPopulationValue.getDefaultValue(DataPopulationValue.FAMILY_DELEGATO_PF.key);
    private String firstName = DataPopulationValue.getDefaultValue(DataPopulationValue.NAME_DELEGATO_PF.key);
    private String fiscalCode = DataPopulationValue.getDefaultValue(DataPopulationValue.FISCAL_CODE_DELEGATO_PF.key);
    private String lastName = DataPopulationValue.getDefaultValue(DataPopulationValue.FAMILY_DELEGATO_PF.key);
    private String fiscalNumber = DataPopulationValue.getDefaultValue(DataPopulationValue.FISCAL_NUMBER_DELEGATO_PF.key);
    private String verificationCode = DataPopulationValue.getDefaultValue(DataPopulationValue.VERIFICATION_CODE_DELEGATO_PF.key);

    //---------------------------------------------------

    //DATI DELEGATO PG
    private String companyName = DataPopulationValue.getDefaultValue(DataPopulationValue.RAGIONE_SOC_DELEGATO_PG.key);
    private String displayNamePG = DataPopulationValue.getDefaultValue(DataPopulationValue.RAGIONE_SOC_DELEGATO_PG.key);
    private String fiscalCodePG = DataPopulationValue.getDefaultValue(DataPopulationValue.FISCAL_CODE_DELEGATO_PG.key);
    private String fiscalNumberPG = DataPopulationValue.getDefaultValue(DataPopulationValue.FISCAL_NUMBER_DELEGATO_PG.key);

    @Value("${delegato.pg.emailPecPG}")
    private String emailPecPG;

    //---------------------------------------------------

    //DATI PERSONA FISICA
    @Value("${persona.fisica.email}")
    private String email;
    @Value("${persona.fisica.email1}")
    private String mail;
    @Value("${persona.fisica.emailPec}")
    private String emailPec;
    @Value("${persona.fisica.emailPec1}")
    private String pec;
    @Value("${persona.fisica.additionalEmail}")
    private String additionalEmail;

    private String emailPecErrore = DataPopulationValue.getDefaultValue(DataPopulationValue.EMAIL_PEC_ERR_PF.key);
    private String pecErrore = DataPopulationValue.getDefaultValue(DataPopulationValue.EMAIL_PEC_ERR_1_PF.key);
    private String telefono = DataPopulationValue.getDefaultValue(DataPopulationValue.TELEFONO_PF.key);
    private String name = DataPopulationValue.getDefaultValue(DataPopulationValue.NAME_PF.key);
    private String familyName = DataPopulationValue.getDefaultValue(DataPopulationValue.FAMILY_NAME_PF.key);
    private String codiceFiscale = DataPopulationValue.getDefaultValue(DataPopulationValue.CODICE_FISCALE_PF.key);
    private String fiscalNumberPersonaFisica = DataPopulationValue.getDefaultValue(DataPopulationValue.FISCAL_NUMBER_PF.key);
    private String indirizzo = DataPopulationValue.getDefaultValue(DataPopulationValue.INDIRIZZO_PF.key);
    private String numeroCivico = DataPopulationValue.getDefaultValue(DataPopulationValue.CIVICO_PF.key);
    private String localita = DataPopulationValue.getDefaultValue(DataPopulationValue.LOCALITA_PF.key);
    private String comune = DataPopulationValue.getDefaultValue(DataPopulationValue.COMUNE_PF.key);
    private String provincia = DataPopulationValue.getDefaultValue(DataPopulationValue.PROVINCIA_PF.key);
    private String codicepostale = DataPopulationValue.getDefaultValue(DataPopulationValue.CAP_PF.key);
    private String stato = DataPopulationValue.getDefaultValue(DataPopulationValue.STATO_PF.key);
    private String OTPpec = DataPopulationValue.getDefaultValue(DataPopulationValue.OTP_PEC_PF.key);
    private String OTPmail = DataPopulationValue.getDefaultValue(DataPopulationValue.OTP_MAIL_PF.key);

    //---------------------------------------------------
    @Getter
    @Setter
    private DatiNotificaPg datiNotificaPg;

    @Getter
    @Setter
    private DelegatePF delegatePF;
    @Getter
    @Setter
    private DelegatePG delegatePG;
    @Getter
    @Setter
    private DelegateRequestPF delegateRequestPF;
    @Getter
    @Setter
    private DelegateRequestPG delegateRequestPG;
    @Getter
    @Setter
    private PersonaFisica personaFisica;
    @Getter
    @Setter
    private Mittente mittente;

    //BEAN DATI NOTIFICA PG
    @Bean
    public DatiNotificaPg datiNotificaPg(){
        datiNotificaPg = new DatiNotificaPg();
        datiNotificaPg.setOggettoDellaNotifica(DataPopulationValue.getDefaultValue(DataPopulationValue.OGGETTO_DELLA_NOTIFICA.key));
        datiNotificaPg.setCodiceIUN(DataPopulationValue.getDefaultValue(DataPopulationValue.CODICE_IUN.key));
        return datiNotificaPg;
    }

    //BEAN DELEGA PF
    @Bean
    public DelegatePF delegatePF() {
        delegatePF = new DelegatePF();
        delegatePF.setPerson(true);
        delegatePF.setDisplayName(displayName);
        delegatePF.setFiscalCode(fiscalCode);
        return delegatePF;
    }

    //BEAN REQUEST DELEGA PF
    @Bean
    public DelegateRequestPF delegateRequestPF() {
        delegateRequestPF = new DelegateRequestPF();
        delegateRequestPF.setDateto(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        delegateRequestPF.setDelegate(delegatePF);
        delegateRequestPF.setVisibilityIds(new ArrayList<String>());
        delegateRequestPF.setVerificationCode(verificationCode);

        return delegateRequestPF;
    }

    //BEAN DELEGA PG
    @Bean
    public DelegatePG delegatePG() {
        delegatePG = new DelegatePG();
        delegatePG.setPerson(false);
        delegatePG.setDisplayName(displayNamePG);
        delegatePG.setCompanyName(companyName);
        delegatePG.setFiscalCode(fiscalCodePG);

        return delegatePG;
    }

    //BEAN REQUEST DELEGA PG
    @Bean
    public DelegateRequestPG delegateRequestPG() {
        delegateRequestPG = new DelegateRequestPG();
        delegateRequestPG.setDateto(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        delegateRequestPG.setDelegate(delegatePG);
        delegateRequestPG.setVisibilityIds(new ArrayList<String>());
        delegateRequestPG.setVerificationCode(verificationCode);

        return delegateRequestPG;
    }

    //BEAN PERSONA FISICA
    @Bean
    public PersonaFisica personafisica() {
        personaFisica = new PersonaFisica();
        personaFisica.setName(name);
        personaFisica.setFamilyName(familyName);
        personaFisica.setCodiceFiscale(codiceFiscale);
        personaFisica.setTelefono(telefono);

        personaFisica.setEmail(email);
        personaFisica.setAdditionalEmail(additionalEmail);
        personaFisica.setEmailPec(emailPec);
        personaFisica.setPec(pec);
        personaFisica.setFiscalNumber(fiscalNumberPersonaFisica);
        personaFisica.setEmailPecErrore(emailPecErrore);
        personaFisica.setPecErrore(pecErrore);
        personaFisica.setStato(stato);

        personaFisica.setIndirizzo(indirizzo);
        personaFisica.setNumeroCivico(numeroCivico);
        personaFisica.setLocalita(localita);
        personaFisica.setComune(comune);
        personaFisica.setProvincia(provincia);
        personaFisica.setCodicepostale(codicepostale);
        personaFisica.setOTPpec(OTPpec);
        personaFisica.setOTPmail(OTPmail);
        return personaFisica;
//        return  new PersonaFisica(); //????????????????????
    }


    //BEAN MITTENTE
    @Bean
    public Mittente mittente() {
        mittente = new Mittente();
        mittente.setUrl(DataPopulationValue.getDefaultValue(DataPopulationValue.URL_MITTENTE.key));
        mittente.setUser(DataPopulationValue.getDefaultValue(DataPopulationValue.USER_MITTENTE.key));
        mittente.setPwd(DataPopulationValue.getDefaultValue(DataPopulationValue.PWD_MITTENTE.key));
        mittente.setProvider(DataPopulationValue.getDefaultValue(DataPopulationValue.PROVIDER_MITTENTE.key));
        mittente.setComune(DataPopulationValue.getDefaultValue(DataPopulationValue.COMUNE.key));
        mittente.setAmbiente(DataPopulationValue.getDefaultValue(DataPopulationValue.ENV.key));
        mittente.setCodiceFiscale(DataPopulationValue.getDefaultValue(DataPopulationValue.FISCAL_CODE_MITTENTE.key));
        mittente.setCodiceAvviso(DataPopulationValue.getDefaultValue(DataPopulationValue.COD_AVVISO.key));
        mittente.setCodiceApiKeyTEST(DataPopulationValue.getDefaultValue(DataPopulationValue.API_KEY_TEST.key));
        mittente.setCodiceApiKeyDEV(DataPopulationValue.getDefaultValue(DataPopulationValue.API_KEY_DEV.key));

        return mittente;
    }


}
