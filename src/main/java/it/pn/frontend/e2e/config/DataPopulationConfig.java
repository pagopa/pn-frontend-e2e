package it.pn.frontend.e2e.config;

import it.pn.frontend.e2e.common.DataPopulationValue;
import it.pn.frontend.e2e.model.delegate.DelegatePF;
import it.pn.frontend.e2e.model.delegate.DelegatePG;
import it.pn.frontend.e2e.model.delegate.DelegateRequestPF;
import it.pn.frontend.e2e.model.delegate.DelegateRequestPG;
import it.pn.frontend.e2e.model.recipients.*;
import it.pn.frontend.e2e.model.recipients.PersonaFisicaPec;
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
    private String firstName = DataPopulationValue.getDefaultValue(DataPopulationValue.NAME_DELEGATO_PF.key);
    private String lastName = DataPopulationValue.getDefaultValue(DataPopulationValue.FAMILY_DELEGATO_PF.key);
    private String fiscalNumber = DataPopulationValue.getDefaultValue(DataPopulationValue.FISCAL_NUMBER_DELEGATO_PF.key);

    //---------------------------------------------------

    //DATI DELEGATO PG

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

    //---------------------------------------------------

    //DATI PERSONA GIURIDICA
    @Value("${persona.giuridica.email}")
    private String emailPg;
    @Value("${persona.giuridica.mail}")
    private String mailPg;
    @Value("${persona.giuridica.emailPec}")
    private String emailPecPg;
    @Value("${persona.giuridica.pec}")
    private String pecPg;

    //---------------------------------------------------
    //DATI PERSONA GIURIDICA 1
    @Value("${persona.giuridica_1.emailPec}")
    private String emailPecPg1;


    @Getter
    @Setter
    private PersonaFisicaPec personaFisicaPec;
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
    @Getter
    @Setter
    private NuovaDelegaPg nuovaDelegaPg;
    @Getter
    @Setter
    private PersonaGiuridica personaGiuridica;
    @Getter
    @Setter
    private PersonaGiuridica1 personaGiuridica1;


    //BEAN DATI NOTIFICA PG
    @Bean
    public PersonaFisicaPec personaFisicaPec(){
        personaFisicaPec = new PersonaFisicaPec();
        personaFisicaPec.setName(DataPopulationValue.getDefaultValue(DataPopulationValue.NAME_PF_PEC.key));
        personaFisicaPec.setFamilyName(DataPopulationValue.getDefaultValue(DataPopulationValue.FAMILYNAME_PF_PEC.key));
        personaFisica.setCodiceFiscale(DataPopulationValue.getDefaultValue(DataPopulationValue.FISCALCODE_PF_PEC.key));
        personaFisica.setEmailPec(DataPopulationValue.getDefaultValue(DataPopulationValue.EMAIL_PF_PEC.key));
        personaFisica.setFiscalNumber(DataPopulationValue.getDefaultValue(DataPopulationValue.FISCALNUMBER_PF_PEC.key));
        personaFisica.setStato(DataPopulationValue.getDefaultValue(DataPopulationValue.STATO_PF_PEC.key));

        personaFisica.setIndirizzo(DataPopulationValue.getDefaultValue(DataPopulationValue.INDIRIZZO_PF_PEC.key));
        personaFisica.setNumeroCivico(DataPopulationValue.getDefaultValue(DataPopulationValue.NUMEROCIVICO_PF_PEC.key));
        personaFisica.setLocalita(DataPopulationValue.getDefaultValue(DataPopulationValue.LOCALITA_PF_PEC.key));
        personaFisica.setComune(DataPopulationValue.getDefaultValue(DataPopulationValue.COMUNE_PF_PEC.key));
        personaFisica.setProvincia(DataPopulationValue.getDefaultValue(DataPopulationValue.PROVINCIA_PF_PEC.key));
        personaFisica.setCodicePostale(DataPopulationValue.getDefaultValue(DataPopulationValue.CAP_PF_PEC.key));
        return personaFisicaPec;

    }
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
        delegatePF.setDisplayName(DataPopulationValue.getDefaultValue(DataPopulationValue.FAMILY_DELEGATO_PF.key));
        delegatePF.setFiscalCode(DataPopulationValue.getDefaultValue(DataPopulationValue.FISCAL_CODE_DELEGATO_PF.key));
        return delegatePF;
    }

    //BEAN REQUEST DELEGA PF
    @Bean
    public DelegateRequestPF delegateRequestPF() {
        delegateRequestPF = new DelegateRequestPF();
        delegateRequestPF.setDateto(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        delegateRequestPF.setDelegate(delegatePF);
        delegateRequestPF.setVisibilityIds(new ArrayList<String>());
        delegateRequestPF.setVerificationCode(DataPopulationValue.getDefaultValue(DataPopulationValue.VERIFICATION_CODE_DELEGATO_PF.key));

        return delegateRequestPF;
    }

    //BEAN DELEGA PG
    @Bean
    public DelegatePG delegatePG() {
        delegatePG = new DelegatePG();
        delegatePG.setPerson(false);
        delegatePG.setDisplayName(DataPopulationValue.getDefaultValue(DataPopulationValue.RAGIONE_SOC_DELEGATO_PG.key));
        delegatePG.setCompanyName(DataPopulationValue.getDefaultValue(DataPopulationValue.RAGIONE_SOC_DELEGATO_PG.key));
        delegatePG.setFiscalCode(DataPopulationValue.getDefaultValue(DataPopulationValue.FISCAL_CODE_DELEGATO_PG.key));

        return delegatePG;
    }

    //BEAN REQUEST DELEGA PG
    @Bean
    public DelegateRequestPG delegateRequestPG() {
        delegateRequestPG = new DelegateRequestPG();
        delegateRequestPG.setDateto(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        delegateRequestPG.setDelegate(delegatePG);
        delegateRequestPG.setVisibilityIds(new ArrayList<String>());
        delegateRequestPG.setVerificationCode(DataPopulationValue.getDefaultValue(DataPopulationValue.VERIFICATION_CODE_DELEGATO_PF.key));

        return delegateRequestPG;
    }

    //BEAN PERSONA FISICA
    @Bean
    public PersonaFisica personafisica() {
        personaFisica = new PersonaFisica();
        personaFisica.setName(DataPopulationValue.getDefaultValue(DataPopulationValue.NAME_PF.key));
        personaFisica.setFamilyName(DataPopulationValue.getDefaultValue(DataPopulationValue.FAMILY_NAME_PF.key));
        personaFisica.setCodiceFiscale(DataPopulationValue.getDefaultValue(DataPopulationValue.CODICE_FISCALE_PF.key));
        personaFisica.setTelefono(DataPopulationValue.getDefaultValue(DataPopulationValue.TELEFONO_PF.key));

        personaFisica.setEmail(email);
        personaFisica.setAdditionalEmail(additionalEmail);
        personaFisica.setEmailPec(emailPec);
        personaFisica.setPec(pec);
        personaFisica.setFiscalNumber(DataPopulationValue.getDefaultValue(DataPopulationValue.FISCAL_NUMBER_PF.key));
        personaFisica.setEmailPecErrore(DataPopulationValue.getDefaultValue(DataPopulationValue.EMAIL_PEC_ERR_PF.key));
        personaFisica.setPecErrore(DataPopulationValue.getDefaultValue(DataPopulationValue.EMAIL_PEC_ERR_1_PF.key));
        personaFisica.setStato(DataPopulationValue.getDefaultValue(DataPopulationValue.STATO_PF.key));

        personaFisica.setIndirizzo(DataPopulationValue.getDefaultValue(DataPopulationValue.INDIRIZZO_PF.key));
        personaFisica.setNumeroCivico(DataPopulationValue.getDefaultValue(DataPopulationValue.CIVICO_PF.key));
        personaFisica.setLocalita(DataPopulationValue.getDefaultValue(DataPopulationValue.LOCALITA_PF.key));
        personaFisica.setComune(DataPopulationValue.getDefaultValue(DataPopulationValue.COMUNE_PF.key));
        personaFisica.setProvincia(DataPopulationValue.getDefaultValue(DataPopulationValue.PROVINCIA_PF.key));
        personaFisica.setCodicePostale(DataPopulationValue.getDefaultValue(DataPopulationValue.CAP_PF.key));
        personaFisica.setOTPPec(DataPopulationValue.getDefaultValue(DataPopulationValue.OTP_PEC_PF.key));
        personaFisica.setOTPMail(DataPopulationValue.getDefaultValue(DataPopulationValue.OTP_MAIL_PF.key));
        return personaFisica;

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

    @Bean
    public NuovaDelegaPg nuovaDelegaPg(){
        nuovaDelegaPg = new NuovaDelegaPg();
        nuovaDelegaPg.setRagioneSociale(DataPopulationValue.getDefaultValue(DataPopulationValue.RAGIONE_SOC_NUOVA_DELEGA_PG.key));
        nuovaDelegaPg.setCodiceFiscale(DataPopulationValue.getDefaultValue(DataPopulationValue.CODICE_FISCALE_NUOVA_DELEGA_PG.key));
        nuovaDelegaPg.setEnte(DataPopulationValue.getDefaultValue(DataPopulationValue.ENTE_DELEGA_NUOVA_DELEGA_PG.key));
        nuovaDelegaPg.setCodiceDelega(DataPopulationValue.getDefaultValue(DataPopulationValue.CODE_DELEGA_NUOVA_DELEGA_PG.key));
        return nuovaDelegaPg;
    }

    @Bean
    public PersonaGiuridica personaGiuridica(){
//        email, mail,emailPec, pec

        personaGiuridica = new PersonaGiuridica();
        personaGiuridica.setEmail(emailPg);
        personaGiuridica.setMail(mailPg);
        personaGiuridica.setEmailPec(emailPecPg);
        personaGiuridica.setPec(pecPg);
        personaGiuridica.setPecErrore(DataPopulationValue.getDefaultValue(DataPopulationValue.PEC_ERRORE_PG.key));
        personaGiuridica.setRagioneSociale(DataPopulationValue.getDefaultValue(DataPopulationValue.RAGIONE_SOCIALE_PG.key));
        personaGiuridica.setCodiceFiscale(DataPopulationValue.getDefaultValue(DataPopulationValue.CODICE_FISCALE_PG.key));
        personaGiuridica.setFiscalNumber(DataPopulationValue.getDefaultValue(DataPopulationValue.FISCAL_NUMBER_PG.key));
        personaGiuridica.setIndirizzo(DataPopulationValue.getDefaultValue(DataPopulationValue.INDIRIZZO_PG.key));
        personaGiuridica.setNumeroCivico(DataPopulationValue.getDefaultValue(DataPopulationValue.NUMERO_CIVICO_PG.key));
        personaGiuridica.setLocalita(DataPopulationValue.getDefaultValue(DataPopulationValue.LOCALITA_PG.key));
        personaGiuridica.setComune(DataPopulationValue.getDefaultValue(DataPopulationValue.COMUNE_PG.key));
        personaGiuridica.setProvincia(DataPopulationValue.getDefaultValue(DataPopulationValue.PROVINCIA_PG.key));
        personaGiuridica.setCodicePostale(DataPopulationValue.getDefaultValue(DataPopulationValue.CODICE_POSTALE_PG.key));
        personaGiuridica.setStato(DataPopulationValue.getDefaultValue(DataPopulationValue.STATO_PG.key));
        personaGiuridica.setCodiceIUN(DataPopulationValue.getDefaultValue(DataPopulationValue.CODICE_IUN_PG.key));
        personaGiuridica.setCellulare(DataPopulationValue.getDefaultValue(DataPopulationValue.CELLULARE_PG.key));
        personaGiuridica.setBearerToken(DataPopulationValue.getDefaultValue(DataPopulationValue.BEARER_TOKEN_PG.key));
        personaGiuridica.setOTPPec(DataPopulationValue.getDefaultValue(DataPopulationValue.OTP_PEC_PG.key));
        personaGiuridica.setOTPMail(DataPopulationValue.getDefaultValue(DataPopulationValue.OTP_MAIL_PG.key));

        return personaGiuridica;

    }

    @Bean
    public PersonaGiuridica1 personaGiuridica1(){

        personaGiuridica1 = new PersonaGiuridica1();

        personaGiuridica1.setEmailPec(emailPecPg1);
        personaGiuridica1.setRagioneSociale(DataPopulationValue.getDefaultValue(DataPopulationValue.RAGIONE_SOCIALE_PG_1.key));
        personaGiuridica1.setCodiceFiscale(DataPopulationValue.getDefaultValue(DataPopulationValue.CODICE_FISCALE_PG_1.key));
        personaGiuridica1.setFiscalNumber(DataPopulationValue.getDefaultValue(DataPopulationValue.FISCAL_NUMBER_PG_1.key));
        personaGiuridica1.setIndirizzo(DataPopulationValue.getDefaultValue(DataPopulationValue.INDIRIZZO_PG_1.key));
        personaGiuridica1.setNumeroCivico(DataPopulationValue.getDefaultValue(DataPopulationValue.NUMERO_CIVICO_PG_1.key));
        personaGiuridica1.setLocalita(DataPopulationValue.getDefaultValue(DataPopulationValue.LOCALITA_PG_1.key));
        personaGiuridica1.setComune(DataPopulationValue.getDefaultValue(DataPopulationValue.COMUNE_PG_1.key));
        personaGiuridica1.setProvincia(DataPopulationValue.getDefaultValue(DataPopulationValue.PROVINCIA_PG_1.key));
        personaGiuridica1.setCodicePostale(DataPopulationValue.getDefaultValue(DataPopulationValue.CODICE_POSTALE_PG_1.key));
        personaGiuridica1.setStato(DataPopulationValue.getDefaultValue(DataPopulationValue.STATO_PG_1.key));
        personaGiuridica.setCodiceIUN(DataPopulationValue.getDefaultValue(DataPopulationValue.CODICE_IUN_PG_1.key));

        return personaGiuridica1;

    }





}
