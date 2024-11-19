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
    private PersoneGiuridiche personeGiuridiche;
    @Getter
    @Setter
    private PersoneFisiche personeFisiche;
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
    @Getter
    @Setter
    private PersonaGiuridicaErrore personaGiuridicaErrore;
    @Getter
    @Setter
    private NuovaDelega nuovaDelega;
    @Getter
    @Setter
    private NuovaDelegaErrore nuovaDelegaErrore;
    @Getter
    @Setter
    private DatiNotifica datiNotifica;
    @Getter
    @Setter
    private DatiNotificaErrore datiNotificaErrore;
    @Getter
    @Setter
    private TestHelpdesk testHelpdesk ;

    @Bean
    public PersoneGiuridiche personeGiuridiche() {
        personeGiuridiche = new PersoneGiuridiche();
        PersonaGiuridica persona1 = new PersonaGiuridica();

        persona1.setName(DataPopulationValue.getDefaultValue(DataPopulationValue.NAME_PG_1.key));
        persona1.setCodiceFiscale(DataPopulationValue.getDefaultValue(DataPopulationValue.CF_PG_1.key));
        persona1.setFiscalNumber(DataPopulationValue.getDefaultValue(DataPopulationValue.FISCAL_NUM_PG_1.key));
        persona1.setIndirizzo(DataPopulationValue.getDefaultValue(DataPopulationValue.INDI_PG_1.key));
        persona1.setNumeroCivico(DataPopulationValue.getDefaultValue(DataPopulationValue.CIV_PG_1.key));
        persona1.setLocalita(DataPopulationValue.getDefaultValue(DataPopulationValue.LOCA_PG_1.key));
        persona1.setComune(DataPopulationValue.getDefaultValue(DataPopulationValue.COM_PG_1.key));
        persona1.setProvincia(DataPopulationValue.getDefaultValue(DataPopulationValue.PR_PG_1.key));
        persona1.setCodicePostale(DataPopulationValue.getDefaultValue(DataPopulationValue.CAP_PG_1.key));
        persona1.setStato(DataPopulationValue.getDefaultValue(DataPopulationValue.ST_PG_1.key));
        personeGiuridiche.aggiungiPersona(persona1);

        PersonaGiuridica persona2 = new PersonaGiuridica();
        persona2.setName(DataPopulationValue.getDefaultValue(DataPopulationValue.NAME_PG_2.key));
        persona2.setCodiceFiscale(DataPopulationValue.getDefaultValue(DataPopulationValue.CODICE_FISCALE_PG_2.key));
        persona2.setFiscalNumber(DataPopulationValue.getDefaultValue(DataPopulationValue.FISCAL_NUMBER_PG_2.key));
        persona2.setIndirizzo(DataPopulationValue.getDefaultValue(DataPopulationValue.INDIRIZZO_PG_2.key));
        persona2.setNumeroCivico(DataPopulationValue.getDefaultValue(DataPopulationValue.CIVICO_PG_2.key));
        persona2.setLocalita(DataPopulationValue.getDefaultValue(DataPopulationValue.LOCALITA_PG_2.key));
        persona2.setComune(DataPopulationValue.getDefaultValue(DataPopulationValue.COMUNE_PG_2.key));
        persona2.setProvincia(DataPopulationValue.getDefaultValue(DataPopulationValue.PROVINCIA_PG_2.key));
        persona2.setCodicePostale(DataPopulationValue.getDefaultValue(DataPopulationValue.CAP_PG_2.key));
        persona2.setStato(DataPopulationValue.getDefaultValue(DataPopulationValue.STATO_PG_2.key));
        personeGiuridiche.aggiungiPersona(persona2);

        PersonaGiuridica persona3 = new PersonaGiuridica();
        persona3.setName(DataPopulationValue.getDefaultValue(DataPopulationValue.NAME_PG_3.key));
        persona3.setCodiceFiscale(DataPopulationValue.getDefaultValue(DataPopulationValue.CODICE_FISCALE_PG_3.key));
        persona3.setFiscalNumber(DataPopulationValue.getDefaultValue(DataPopulationValue.FISCAL_NUMBER_PG_3.key));
        persona3.setIndirizzo(DataPopulationValue.getDefaultValue(DataPopulationValue.INDIRIZZO_PG_3.key));
        persona3.setNumeroCivico(DataPopulationValue.getDefaultValue(DataPopulationValue.CIVICO_PG_3.key));
        persona3.setLocalita(DataPopulationValue.getDefaultValue(DataPopulationValue.LOCALITA_PG_3.key));
        persona3.setComune(DataPopulationValue.getDefaultValue(DataPopulationValue.COMUNE_PG_3.key));
        persona3.setProvincia(DataPopulationValue.getDefaultValue(DataPopulationValue.PROVINCIA_PG_3.key));
        persona3.setCodicePostale(DataPopulationValue.getDefaultValue(DataPopulationValue.CAP_PG_3.key));
        persona3.setStato(DataPopulationValue.getDefaultValue(DataPopulationValue.STATO_PG_3.key));
        personeGiuridiche.aggiungiPersona(persona3);

        PersonaGiuridica persona4 = new PersonaGiuridica();
        persona4.setName(DataPopulationValue.getDefaultValue(DataPopulationValue.NAME_PG_4.key));
        persona4.setCodiceFiscale(DataPopulationValue.getDefaultValue(DataPopulationValue.CODICE_FISCALE_PG_4.key));
        persona4.setFiscalNumber(DataPopulationValue.getDefaultValue(DataPopulationValue.FISCAL_NUMBER_PG_4.key));
        persona4.setIndirizzo(DataPopulationValue.getDefaultValue(DataPopulationValue.INDIRIZZO_PG_4.key));
        persona4.setNumeroCivico(DataPopulationValue.getDefaultValue(DataPopulationValue.CIVICO_PG_4.key));
        persona4.setLocalita(DataPopulationValue.getDefaultValue(DataPopulationValue.LOCALITA_PG_4.key));
        persona4.setComune(DataPopulationValue.getDefaultValue(DataPopulationValue.COMUNE_PG_4.key));
        persona4.setProvincia(DataPopulationValue.getDefaultValue(DataPopulationValue.PROVINCIA_PG_4.key));
        persona4.setCodicePostale(DataPopulationValue.getDefaultValue(DataPopulationValue.CAP_PG_4.key));
        persona4.setStato(DataPopulationValue.getDefaultValue(DataPopulationValue.STATO_PG_4.key));
        personeGiuridiche.aggiungiPersona(persona4);

        return personeGiuridiche;
    }

    @Bean
    public PersoneFisiche personeFisiche() {
        personeFisiche = new PersoneFisiche();
        PersonaFisica persona1 = new PersonaFisica();

        persona1.setName(DataPopulationValue.getDefaultValue(DataPopulationValue.NAME_PF_1.key));
        persona1.setFamilyName(DataPopulationValue.getDefaultValue(DataPopulationValue.FAMILY_NAME_PF_1.key));
        persona1.setCodiceFiscale(DataPopulationValue.getDefaultValue(DataPopulationValue.CODICE_FISCALE_PF_1.key));
        persona1.setFiscalNumber(DataPopulationValue.getDefaultValue(DataPopulationValue.FISCAL_NUMBER_PF_1.key));
        persona1.setIndirizzo(DataPopulationValue.getDefaultValue(DataPopulationValue.INDIRIZZO_PF_1.key));
        persona1.setNumeroCivico(DataPopulationValue.getDefaultValue(DataPopulationValue.CIVICO_PF_1.key));
        persona1.setLocalita(DataPopulationValue.getDefaultValue(DataPopulationValue.LOCALITA_PF_1.key));
        persona1.setComune(DataPopulationValue.getDefaultValue(DataPopulationValue.COMUNE_PF_1.key));
        persona1.setProvincia(DataPopulationValue.getDefaultValue(DataPopulationValue.PROVINCIA_PF_1.key));
        persona1.setCodicePostale(DataPopulationValue.getDefaultValue(DataPopulationValue.CAP_PF_1.key));
        persona1.setStato(DataPopulationValue.getDefaultValue(DataPopulationValue.STATO_PF_1.key));
        personeFisiche.aggiungiPersona(persona1);

        PersonaFisica persona2 = new PersonaFisica();
        persona2.setName(DataPopulationValue.getDefaultValue(DataPopulationValue.NAME_PF_2.key));
        persona2.setFamilyName(DataPopulationValue.getDefaultValue(DataPopulationValue.FAMILY_NAME_PF_2.key));
        persona2.setCodiceFiscale(DataPopulationValue.getDefaultValue(DataPopulationValue.CODICE_FISCALE_PF_2.key));
        persona2.setFiscalNumber(DataPopulationValue.getDefaultValue(DataPopulationValue.FISCAL_NUMBER_PF_2.key));
        persona2.setIndirizzo(DataPopulationValue.getDefaultValue(DataPopulationValue.INDIRIZZO_PF_2.key));
        persona2.setNumeroCivico(DataPopulationValue.getDefaultValue(DataPopulationValue.CIVICO_PF_2.key));
        persona2.setLocalita(DataPopulationValue.getDefaultValue(DataPopulationValue.LOCALITA_PF_2.key));
        persona2.setComune(DataPopulationValue.getDefaultValue(DataPopulationValue.COMUNE_PF_2.key));
        persona2.setProvincia(DataPopulationValue.getDefaultValue(DataPopulationValue.PROVINCIA_PF_2.key));
        persona2.setCodicePostale(DataPopulationValue.getDefaultValue(DataPopulationValue.CAP_PF_2.key));
        persona2.setStato(DataPopulationValue.getDefaultValue(DataPopulationValue.STATO_PF_2.key));
        personeFisiche.aggiungiPersona(persona2);

        PersonaFisica persona3 = new PersonaFisica();
        persona3.setName(DataPopulationValue.getDefaultValue(DataPopulationValue.NAME_PF_3.key));
        persona3.setFamilyName(DataPopulationValue.getDefaultValue(DataPopulationValue.FAMILY_NAME_PF_3.key));
        persona3.setCodiceFiscale(DataPopulationValue.getDefaultValue(DataPopulationValue.CODICE_FISCALE_PF_3.key));
        persona3.setFiscalNumber(DataPopulationValue.getDefaultValue(DataPopulationValue.FISCAL_NUMBER_PF_3.key));
        persona3.setIndirizzo(DataPopulationValue.getDefaultValue(DataPopulationValue.INDIRIZZO_PF_3.key));
        persona3.setNumeroCivico(DataPopulationValue.getDefaultValue(DataPopulationValue.CIVICO_PF_3.key));
        persona3.setLocalita(DataPopulationValue.getDefaultValue(DataPopulationValue.LOCALITA_PF_3.key));
        persona3.setComune(DataPopulationValue.getDefaultValue(DataPopulationValue.COMUNE_PF_3.key));
        persona3.setProvincia(DataPopulationValue.getDefaultValue(DataPopulationValue.PROVINCIA_PF_3.key));
        persona3.setCodicePostale(DataPopulationValue.getDefaultValue(DataPopulationValue.CAP_PF_3.key));
        persona3.setStato(DataPopulationValue.getDefaultValue(DataPopulationValue.STATO_PF_3.key));
        personeFisiche.aggiungiPersona(persona3);

        PersonaFisica persona4 = new PersonaFisica();
        persona4.setName(DataPopulationValue.getDefaultValue(DataPopulationValue.NAME_PF_4.key));
        persona4.setFamilyName(DataPopulationValue.getDefaultValue(DataPopulationValue.FAMILY_NAME_PF_4.key));
        persona4.setCodiceFiscale(DataPopulationValue.getDefaultValue(DataPopulationValue.CODICE_FISCALE_PF_4.key));
        persona4.setFiscalNumber(DataPopulationValue.getDefaultValue(DataPopulationValue.FISCAL_NUMBER_PF_4.key));
        persona4.setIndirizzo(DataPopulationValue.getDefaultValue(DataPopulationValue.INDIRIZZO_PF_4.key));
        persona4.setNumeroCivico(DataPopulationValue.getDefaultValue(DataPopulationValue.CIVICO_PF_4.key));
        persona4.setLocalita(DataPopulationValue.getDefaultValue(DataPopulationValue.LOCALITA_PF_4.key));
        persona4.setComune(DataPopulationValue.getDefaultValue(DataPopulationValue.COMUNE_PF_4.key));
        persona4.setProvincia(DataPopulationValue.getDefaultValue(DataPopulationValue.PROVINCIA_PF_4.key));
        persona4.setCodicePostale(DataPopulationValue.getDefaultValue(DataPopulationValue.CAP_PF_4.key));
        persona4.setStato(DataPopulationValue.getDefaultValue(DataPopulationValue.STATO_PF_4.key));
        personeFisiche.aggiungiPersona(persona4);

        return personeFisiche;
    }


    //BEAN DATI NOTIFICA PG
    @Bean
    public PersonaFisicaPec personaFisicaPec(){
        personaFisicaPec = new PersonaFisicaPec();
        personaFisicaPec.setName(DataPopulationValue.getDefaultValue(DataPopulationValue.NAME_PF_PEC.key));
        personaFisicaPec.setFamilyName(DataPopulationValue.getDefaultValue(DataPopulationValue.FAMILYNAME_PF_PEC.key));
        personaFisicaPec.setCodiceFiscale(DataPopulationValue.getDefaultValue(DataPopulationValue.FISCALCODE_PF_PEC.key));
        personaFisicaPec.setEmailPec(DataPopulationValue.getDefaultValue(DataPopulationValue.EMAIL_PF_PEC.key));
        personaFisicaPec.setFiscalNumber(DataPopulationValue.getDefaultValue(DataPopulationValue.FISCALNUMBER_PF_PEC.key));
        personaFisicaPec.setStato(DataPopulationValue.getDefaultValue(DataPopulationValue.STATO_PF_PEC.key));

        personaFisicaPec.setIndirizzo(DataPopulationValue.getDefaultValue(DataPopulationValue.INDIRIZZO_PF_PEC.key));
        personaFisicaPec.setNumeroCivico(DataPopulationValue.getDefaultValue(DataPopulationValue.NUMEROCIVICO_PF_PEC.key));
        personaFisicaPec.setLocalita(DataPopulationValue.getDefaultValue(DataPopulationValue.LOCALITA_PF_PEC.key));
        personaFisicaPec.setComune(DataPopulationValue.getDefaultValue(DataPopulationValue.COMUNE_PF_PEC.key));
        personaFisicaPec.setProvincia(DataPopulationValue.getDefaultValue(DataPopulationValue.PROVINCIA_PF_PEC.key));
        personaFisicaPec.setCodicePostale(DataPopulationValue.getDefaultValue(DataPopulationValue.CAP_PF_PEC.key));
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

    @Bean
    public PersonaGiuridicaErrore personaGiuridicaErrore(){

        personaGiuridicaErrore = new PersonaGiuridicaErrore();
        personaGiuridica1.setEmailPec(DataPopulationValue.getDefaultValue(DataPopulationValue.EMAIL_PEC_PG_ERRORE.key));
        personaGiuridica1.setCodiceFiscale(DataPopulationValue.getDefaultValue(DataPopulationValue.CODICE_FISCALE_PG_ERRORE.key));
        return personaGiuridicaErrore;

    }

    @Bean
    public NuovaDelega nuovaDelega(){

        nuovaDelega = new NuovaDelega();
        nuovaDelega.setNome(DataPopulationValue.getDefaultValue(DataPopulationValue.NAME_DELEGA.key));
        nuovaDelega.setCognome(DataPopulationValue.getDefaultValue(DataPopulationValue.SURNAME_DELEGA.key));
        nuovaDelega.setCodiceFiscale(DataPopulationValue.getDefaultValue(DataPopulationValue.FISCAL_CODE_DELEGA.key));
        nuovaDelega.setEnte(DataPopulationValue.getDefaultValue(DataPopulationValue.ENTE_DELEGA.key));
        nuovaDelega.setCodiceDelega(DataPopulationValue.getDefaultValue(DataPopulationValue.CODE_DELEGA.key));
        nuovaDelega.setRagioneSociale(DataPopulationValue.getDefaultValue(DataPopulationValue.RAG_SOC_DELEGA.key));
        return nuovaDelega;

    }

    @Bean
    public NuovaDelegaErrore nuovaDelegaErrore(){

        nuovaDelegaErrore = new NuovaDelegaErrore();
        nuovaDelega.setNome(DataPopulationValue.getDefaultValue(DataPopulationValue.NAME_DELEGA_ERR.key));
        nuovaDelega.setCognome(DataPopulationValue.getDefaultValue(DataPopulationValue.SURNAME_DELEGA_ERR.key));
        nuovaDelega.setCodiceFiscale(DataPopulationValue.getDefaultValue(DataPopulationValue.FISCAL_CODE_DELEGA_ERR.key));
        nuovaDelega.setEnte(DataPopulationValue.getDefaultValue(DataPopulationValue.ENTE_ERR.key));
        nuovaDelega.setCodiceDelega(DataPopulationValue.getDefaultValue(DataPopulationValue.CODE_DELEGA_ERR.key));
        return nuovaDelegaErrore;

    }

    @Bean
    public DatiNotifica datiNotifica(){
        datiNotifica = new DatiNotifica();
        datiNotifica.setNumeroProtocollo(DataPopulationValue.getDefaultValue(DataPopulationValue.NUMERO_PROTOCOLLO_DN.key));
        datiNotifica.setOggettoDellaNotifica(DataPopulationValue.getDefaultValue(DataPopulationValue.OGGETTO_DELLA_NOTIFICA_DN.key));
        datiNotifica.setDescrizione(DataPopulationValue.getDefaultValue(DataPopulationValue.DESCRIZIONE_DN.key));
        datiNotifica.setGruppoTest(DataPopulationValue.getDefaultValue(DataPopulationValue.GRUPPO_TEST_DN.key));
        datiNotifica.setGruppoDev(DataPopulationValue.getDefaultValue(DataPopulationValue.GRUPPO_DEV_DN.key));
        datiNotifica.setCodiceTassonometrico(DataPopulationValue.getDefaultValue(DataPopulationValue.CODICE_TASSONOMETRICO_DN.key));
        datiNotifica.setNomeDocumentoNotifica(DataPopulationValue.getDefaultValue(DataPopulationValue.NOME_DOCUMENTO_NOTIFICA_DN.key));
        datiNotifica.setCodiceIUN(DataPopulationValue.getDefaultValue(DataPopulationValue.CODICE_IUN_DN.key));
        return datiNotifica;

    }

    @Bean
    public DatiNotificaErrore datiNotificaErrore(){
        datiNotificaErrore = new DatiNotificaErrore();
        datiNotificaErrore.setOggettoDellaNotifica(DataPopulationValue.getDefaultValue(DataPopulationValue.OGGETTO_DELLA_NOTIFICA_ERRORE.key));
        datiNotificaErrore.setOggettoDellaNotifica(DataPopulationValue.getDefaultValue(DataPopulationValue.CODICE_TASSONOMETRICO_ERRORE.key));
        return datiNotificaErrore;

    }


    @Bean
    public TestHelpdesk testHelpdesk(){
        testHelpdesk = new TestHelpdesk();
        testHelpdesk.setUrl(DataPopulationValue.getDefaultValue(DataPopulationValue.URL_TH.key));
        testHelpdesk.setUserDev(DataPopulationValue.getDefaultValue(DataPopulationValue.USER_DEV_TH.key));
        testHelpdesk.setPwdDev(DataPopulationValue.getDefaultValue(DataPopulationValue.PWD_DEV_TH.key));
        testHelpdesk.setUserUat(DataPopulationValue.getDefaultValue(DataPopulationValue.USER_UAT_TH.key));
        testHelpdesk.setPwdUat(DataPopulationValue.getDefaultValue(DataPopulationValue.PWD_UAT_TH.key));
        testHelpdesk.setUserTest(DataPopulationValue.getDefaultValue(DataPopulationValue.USER_TEST_TH.key));
        testHelpdesk.setPwdTest(DataPopulationValue.getDefaultValue(DataPopulationValue.PWD_TEST_TH.key));
        testHelpdesk.setCfPf(DataPopulationValue.getDefaultValue(DataPopulationValue.CFPF_TH.key));
        return testHelpdesk;
    }



}
