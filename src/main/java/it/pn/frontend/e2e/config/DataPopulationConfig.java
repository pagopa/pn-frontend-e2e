package it.pn.frontend.e2e.config;

import it.pn.frontend.e2e.common.DataPopulationValue;
import it.pn.frontend.e2e.model.delegate.DelegatePF;
import it.pn.frontend.e2e.model.delegate.DelegatePG;
import it.pn.frontend.e2e.model.delegate.DelegateRequestPF;
import it.pn.frontend.e2e.model.delegate.DelegateRequestPG;
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


    private String displayName = DataPopulationValue.getDefaultValue(DataPopulationValue.FAMILY_DELEGATO_PF.key);
    private String firstName = DataPopulationValue.getDefaultValue(DataPopulationValue.NAME_DELEGATO_PF.key);
    private String fiscalCode = DataPopulationValue.getDefaultValue(DataPopulationValue.FISCAL_CODE_DELEGATO_PF.key);
    private String lastName = DataPopulationValue.getDefaultValue(DataPopulationValue.FAMILY_DELEGATO_PF.key);
    private String fiscalNumber = DataPopulationValue.getDefaultValue(DataPopulationValue.FISCAL_NUMBER_DELEGATO_PF.key);
    private String verificationCode = DataPopulationValue.getDefaultValue(DataPopulationValue.VERIFICATION_CODE_DELEGATO_PF.key);

    @Value("${delegato.pg.ragioneSociale}")
    private String companyName;
    @Value("${delegato.pg.displayName}")
    private String displayNamePG;
    @Value("${delegato.pg.codiceFiscale}")
    private String fiscalCodePG;
    @Value("${delegato.pg.codiceFiscale}")
    private String emailPecPG;
    @Value("${delegato.pg.fiscalNumber}")
    private String fiscalNumberPG;



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


    @Value("${persona.fisica.emailPecErrore}")
    private String emailPecErrore;
    @Value("${persona.fisica.emailPecErrore1}")
    private String pecErrore;
    @Value("${persona.fisica.telefono}")
    private String telefono;
    @Value("${persona.fisica.name}")
    private String name;
    @Value("${persona.fisica.familyName}")
    private String familyName;
    @Value("${persona.fisica.codiceFiscale}")
    private String codiceFiscale;
    @Value("${persona.fisica.fiscalNumber}")
    private String fiscalNumberPersonaFisica;
    @Value("${persona.fisica.indirizzo}")
    private String indirizzo;
    @Value("${persona.fisica.numeroCivico}")
    private String numeroCivico;
    @Value("${persona.fisica.localita}")
    private String localita;
    @Value("${persona.fisica.comune}")
    private String comune;
    @Value("${persona.fisica.provincia}")
    private String provincia;
    @Value("${persona.fisica.codicepostale}")
    private String codicepostale;
    @Value("${persona.fisica.stato}")
    private String stato;
    @Value("${persona.fisica.OTPpec}")
    private String OTPpec;
    @Value("${persona.fisica.OTPmail}")
    private String OTPmail;








    @Getter
    @Setter
    private DelegatePF delegatePF;
    @Getter
    @Setter
    private DelegatePG delegatePG;
    @Getter
    private DelegateRequestPF delegateRequestPF;
    @Getter
    private DelegateRequestPG delegateRequestPG;

    @Getter
    @Setter
    private PersonaFisica personaFisica;








    @Bean
    public DelegatePF delegatePF(){
        delegatePF = new DelegatePF();
        delegatePF.setPerson(true);
        delegatePF.setDisplayName(displayName);
        delegatePF.setFiscalCode(fiscalCode);
        return delegatePF;

    }

    @Bean
    public DelegateRequestPF delegateRequestPF(){
        delegateRequestPF = new DelegateRequestPF();
        delegateRequestPF.setDateto(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        delegateRequestPF.setDelegate(delegatePF);
        delegateRequestPF.setVisibilityIds(new ArrayList<String>());
        delegateRequestPF.setVerificationCode(verificationCode);

        return delegateRequestPF;

    }


    @Bean
    public DelegatePG delegatePG(){
        delegatePG = new DelegatePG();
        delegatePG.setPerson(false);
        delegatePG.setDisplayName(displayNamePG);
        delegatePG.setCompanyName(companyName);
        delegatePG.setFiscalCode(fiscalCodePG);
        return delegatePG;
    }

    @Bean
    public DelegateRequestPG delegateRequestPG(){
        delegateRequestPG = new DelegateRequestPG();
        delegateRequestPG.setDateto(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        delegateRequestPG.setDelegate(delegatePG);
        delegateRequestPG.setVisibilityIds(new ArrayList<String>());
        delegateRequestPG.setVerificationCode(verificationCode);

        return delegateRequestPG;

    }

    @Bean
    public PersonaFisica personafisica(){

        return  new PersonaFisica();
    }







}
