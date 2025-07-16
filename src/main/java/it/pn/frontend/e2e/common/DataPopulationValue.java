package it.pn.frontend.e2e.common;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Random;

public enum DataPopulationValue {

    NAME_PF_1("name_pf_1","Giovanna",false),
    FAMILY_NAME_PF_1("family_name_pf_1","D'arco",false),
    CODICE_FISCALE_PF_1("codiceFiscale_pf_1","DRCGNN12A46A326K",false),
    FISCAL_NUMBER_PF_1("fiscal_number_pf_1","TINIT-DRCGNN12A46A326K",false),
    INDIRIZZO_PF_1("indirizzo_pf_1","VIA VERDI",false),
    CIVICO_PF_1("numeroCivico_pf_1","10",false),
    LOCALITA_PF_1("localita_pf_1","MILANO",false),
    COMUNE_PF_1("comune_pf_1","MILANO",false),
    PROVINCIA_PF_1("provincia_pf_1","MI",false),
    CAP_PF_1("codicepostale_pf_1","20122",false),
    STATO_PF_1("stato_pf_1","ITALIA",false),

    //PERSONA FISICA 2

    NAME_PF_2("name_pf_2","Ada",false),
    FAMILY_NAME_PF_2("family_name_pf_2","Lovelace",false),
    CODICE_FISCALE_PF_2("codiceFiscale_pf_2","LVLDAA85T50G702B",false),
    FISCAL_NUMBER_PF_2("fiscal_number_pf_2","TINIT-LVLDAA85T50G702B",false),
    INDIRIZZO_PF_2("indirizzo_pf_2","VIA TORINO",false),
    CIVICO_PF_2("numeroCivico_pf_2","15",false),
    LOCALITA_PF_2("localita_pf_2","CINISELLO BALSAMO",false),
    COMUNE_PF_2("comune_pf_2","CINISELLO BALSAMO",false),
    PROVINCIA_PF_2("provincia_pf_2","MI",false),
    CAP_PF_2("codicepostale_pf_2","20092",false),
    STATO_PF_2("stato_pf_2","ITALIA",false),

    //PERSONA FISICA 3
    NAME_PF_3("name_pf_3","Lucrezia",false),
    FAMILY_NAME_PF_3("family_name_pf_3","Borgia",false),
    CODICE_FISCALE_PF_3("codiceFiscale_pf_3","BRGLRZ80D58H501Q",false),
    FISCAL_NUMBER_PF_3("fiscal_number_pf_3","TINIT-BRGLRZ80D58H501Q",false),
    INDIRIZZO_PF_3("indirizzo_pf_3","VIA MEDA",false),
    CIVICO_PF_3("numeroCivico_pf_3","9",false),
    LOCALITA_PF_3("localita_pf_3","SEREGNO",false),
    COMUNE_PF_3("comune_pf_3","SEREGNO",false),
    PROVINCIA_PF_3("provincia_pf_3","MB",false),
    CAP_PF_3("codicepostale_pf_3","20831",false),
    STATO_PF_3("stato_pf_3","ITALIA",false),


    //PERSONA FISICA 4
    NAME_PF_4("name_pf_4","Cleopatra Tea",false),
    FAMILY_NAME_PF_4("family_name_pf_4","Filopatore",false),
    CODICE_FISCALE_PF_4("codiceFiscale_pf_4","FLPCPT69A65Z336P",false),
    FISCAL_NUMBER_PF_4("fiscal_number_pf_4","TINIT-FLPCPT69A65Z336P",false),
    INDIRIZZO_PF_4("indirizzo_pf_4","VIA EUROPA",false),
    CIVICO_PF_4("numeroCivico_pf_4","6",false),
    LOCALITA_PF_4("localita_pf_4","TORINO",false),
    COMUNE_PF_4("comune_pf_4","TORINO",false),
    PROVINCIA_PF_4("provincia_pf_4","TO",false),
    CAP_PF_4("codicepostale_pf_4","10024",false),
    STATO_PF_4("stato_pf_4","ITALIA",false),

    NAME_PF_5("name_pf_5","Giovanna",false),
    FAMILY_NAME_PF_5("family_name_pf_5","D'arco",false),
    CODICE_FISCALE_PF_5("codiceFiscale_pf_5","DRCGNN12A46A326K",false),
    FISCAL_NUMBER_PF_5("fiscal_number_pf_5","TINIT-DRCGNN12A46A326K",false),
    INDIRIZZO_PF_5("indirizzo_pf_5","VIA VERDI",false),
    CIVICO_PF_5("numeroCivico_pf_5","10",false),
    LOCALITA_PF_5("localita_pf_5","MILANO",false),
    COMUNE_PF_5("comune_pf_5","MILANO",false),
    PROVINCIA_PF_5("provincia_pf_5","MI",false),
    CAP_PF_5("codicepostale_pf_5","20122",false),
    STATO_PF_5("stato_pf_5","ITALIA",false),


    //PERSONA GIURIDICA 1

    NAME_PG_1("name_pg_1","LucioAnneoSeneca",false),
    CF_PG_1("codiceFiscale_pg_1","20517490320",false),
    FISCAL_NUM_PG_1("fiscal_number_pg_1","TINIT-DRCGNN12A46A326K",false),
    INDI_PG_1("indirizzo_pg_1","VIA VERDI",false),
    CIV_PG_1("numeroCivico_pg_1","10",false),
    LOCA_PG_1("localita_pg_1","MILANO",false),
    COM_PG_1("comune_pg_1","MILANO",false),
    PR_PG_1("provincia_pg_1","MI",false),
    CAP_PG_1("codicepostale_pg_1","20122",false),
    ST_PG_1("stato_pg_1","ITALIA",false),

    //PERSONA GIURIDICA 2

    NAME_PG_2("name_pg_2","Ada",false),
    CODICE_FISCALE_PG_2("codiceFiscale_pg_2","LVLDAA85T50G702B",false),
    FISCAL_NUMBER_PG_2("fiscal_number_pg_2","TINIT-LVLDAA85T50G702B",false),
    INDIRIZZO_PG_2("indirizzo_pg_2","VIA TORINO",false),
    CIVICO_PG_2("numeroCivico_pg_2","15",false),
    LOCALITA_PG_2("localita_pg_2","CINISELLO BALSAMO",false),
    COMUNE_PG_2("comune_pg_2","CINISELLO BALSAMO",false),
    PROVINCIA_PG_2("provincia_pg_2","MI",false),
    CAP_PG_2("codicepostale_pg_2","20092",false),
    STATO_PG_2("stato_pg_2","ITALIA",false),

    //PERSONA GIURIDICA 3
    NAME_PG_3("name_pg_3","Marco",false),
    CODICE_FISCALE_PG_3("codiceFiscale_pg_3","PLOMRC01P30L736Y",false),
    FISCAL_NUMBER_PG_3("fiscal_number_pg_3","TINIT-PLOMRC01P30L736Y",false),
    INDIRIZZO_PG_3("indirizzo_pg_3","VIA MEDA",false),
    CIVICO_PG_3("numeroCivico_pg_3","9",false),
    LOCALITA_PG_3("localita_pg_3","SEREGNO",false),
    COMUNE_PG_3("comune_pg_3","SEREGNO",false),
    PROVINCIA_PG_3("provincia_pg_3","MB",false),
    CAP_PG_3("codicepostale_pg_3","20831",false),
    STATO_PG_3("stato_pg_3","ITALIA",false),


    //PERSONA GIURIDICA 4
    NAME_PG_4("name_pg_4","Cleopatra Tea",false),
    CODICE_FISCALE_PG_4("codiceFiscale_pg_4","FLPCPT69A65Z336P",false),
    FISCAL_NUMBER_PG_4("fiscal_number_pg_4","TINIT-FLPCPT69A65Z336P",false),
    INDIRIZZO_PG_4("indirizzo_pg_4","VIA EUROPA",false),
    CIVICO_PG_4("numeroCivico_pg_4","6",false),
    LOCALITA_PG_4("localita_pg_4","TORINO",false),
    COMUNE_PG_4("comune_pg_4","TORINO",false),
    PROVINCIA_PG_4("provincia_pg_4","TO",false),
    CAP_PG_4("codicepostale_pg_4","10024",false),
    STATO_PG_4("stato_pg_4","ITALIA",false),

    //DATA NOTIFICA PG
    OGGETTO_DELLA_NOTIFICA("oggettoDellaNotifica","Pagamento rata IMU",true),
    CODICE_IUN("codiceIUN","EGNM-DPAR-VTLR-202401-T-1",false),

    //DELEGATO PF NOTIFICA
    URL_DELEGATO_PF("url_delegato_pf","https://cittadini.dev.notifichedigitali.it/",false),
    USER_DELEGATO_PF("user_delegato_pf","lucrezia",false),
    DISPLAY_NAME_DELEGATO_PF("display_name_delegato_pf","Lucrezia Borgia",false),
    PWD_DELEGATO_PF("pwd_delegato_pf","password123",false),
    PROVIDER_DELEGATO_PF("provider_delegato_pf","spid:test",false),
    NAME_DELEGATO_PF("name_delegato_pf","Lucrezia",false),
    FAMILY_DELEGATO_PF("familyName_delegato_pf","Borgia",false),
    FISCAL_CODE_DELEGATO_PF("codiceFiscale_delegato_pf","BRGLRZ80D58H501Q",false),
    FISCAL_NUMBER_DELEGATO_PF("fiscalNumber_delegato_pf","TINIT-BRGLRZ80D58H501Q",false),
    STATE_DELEGATO_PF("stato_delegato_pf","ITALIA",false),
    VERIFICATION_CODE_DELEGATO_PF("verification_code_delegato_pf","12345",false),

    //DELEGATO PG NOTIFICA
    URL_DELEGATO_PG("url_delegato_pg","https://imprese.dev.notifichedigitali.it/",false),
    USER_DELEGATO_PG("user_delegato_pg","FrancescoPetrarca",false),
    PWD_DELEGATO_PG("pwd_delegato_pg","test",false),
    PROVIDER_DELEGATO_PG("provider_delegato_pg","spid:test",false),
    RAGIONE_SOC_DELEGATO_PG("ragioneSociale_delegato_pg","Le Epistolae srl",false),
    PEC_DELEGATO_PG("emailPec_delegato_pg","DanteAlighieri@paradiso.it",false),
    FISCAL_CODE_DELEGATO_PG("codiceFiscale_delegato_pg","LELPTR04A01C352E",false),
    FISCAL_NUMBER_DELEGATO_PG("fiscalNumber_delegato_pg","TINIT-LELPTR04A01C352E",false),

    //MITTENTE
    URL_MITTENTE("url_mittente","https://selfcare.dev.notifichedigitali.it",false),
    USER_MITTENTE("user_mittente","albino63",false),
    PWD_MITTENTE("pwd_mittente","test",false),
    PROVIDER_MITTENTE("provider_mittente","spid:test",false),
    COMUNE("comune_mittente","Verona",false),
    COMUNE_VIGGIU("comune_mittente_viggiu","Viggiu",false),
    ENV("ambiente_mittente","dev",false),
    FISCAL_CODE_MITTENTE("codiceFiscale_mittente","00189800204",false),
    COD_AVVISO("codiceAvviso","302047770009990299",false),
    API_KEY_TEST("codiceApiKeyTEST","2b3d47f4-44c1-4b49-b6ef-54dc1c531311",false),
    API_KEY_DEV("codiceApiKeyDEV","a9f0508d-c344-4347-807f-343bc8210996",false),
    API_KEY_UAT("codiceApiKeyUAT","776c8ea9-1adb-4518-8593-76890d788758",false),

    //NUOVA DELEGA
    NAME_DELEGA("nome_delega_pf","Lucrezia",false),
    SURNAME_DELEGA("cognome_delega_pf","Borgia",false),
    FISCAL_CODE_DELEGA("codiceFiscale_delega_pf","BRGLRZ80D58H501Q",false),
    ENTE_DELEGA("ente_delega_pf","Comune di Verona",false),
    CODE_DELEGA("codiceDelega_pf","29693",false),
    RAG_SOC_DELEGA("ragioneSociale_delega_pf","Lucrezia Borgia",false),

    //NUOVA DELEGA ERR
    NAME_DELEGA_ERR("nome_delega_err_pf","Gaio Giulio",false),
    SURNAME_DELEGA_ERR("cognome_delega_err_pf","Cesare",false),
    FISCAL_CODE_DELEGA_ERR("codiceFiscale_delega_err_pf","CSRGGL44L13H501E",false),
    ENTE_ERR("ente_delega_err_pf","Comune di Palermo",false),
    CODE_DELEGA_ERR("codiceDelega_delega_err_pf","22611",false),

    //Cambiato da Epistolae Srl a Vita Nova Sas per CF non più in formato valido (PN-11148)
    //NUOVA DELEGA PG
    RAGIONE_SOC_NUOVA_DELEGA_PG("ragionesociale_nuova_delega_pg","Vita Nova Sas",false),
    CODICE_FISCALE_NUOVA_DELEGA_PG("codicefiscale_nuova_delega_pg","12666810299",false),
    ENTE_DELEGA_NUOVA_DELEGA_PG("ente_nuova_delega_pg","Comune di Palermo",false),
    CODE_DELEGA_NUOVA_DELEGA_PG("codicedelega_nuova_delega_pg","10166",false),

    //HELP_DESK
    URL_HELP_DESK("url_helpDesk","https://helpdesk.dev.notifichedigitali.it/login",false),
    USER_DEV_HELP_DESK("userDev","test@test.com",false),
    PWD_DEV_HELP_DESK("pwdDev","Test_Cognito_1.!",false),
    USER_UAT_HELP_DESK("userUat","admin@uat.pagopa.it",false),
    PWD_UAT_HELP_DESK("pwdDev","Admin-uatcognito1",false),
    USER_TEST_HELP_DESK("userTest","admin@test.pagopa.it",false),
    PWD_TEST_HELP_DESK("pwdTest","Admin-testcognito1",false),
    CFPF_TEST_HELP_DESK("CfPf","CLMCST42R12D969Z",false),

    //PERSONA FISICA
    URL_PF("url_pf","https://cittadini.dev.notifichedigitali.it/",false),
    EMAIL_PF("email_pf","provaemail@test.it",false),
    USER_PF("user_pf","cesare",false),
    PWD_PF("pwd_pf","password123",false),
    EMAIL_PEC_ERR_PF("email_pec_err_pf","testpagopa2@fail.it",false),
    EMAIL_PEC_ERR_1_PF("email_pec_err_1_pf","testpagopa2@@pnpagopa.postecert.local",false),
    TELEFONO_PF("telefono_pf","3409876543",false),
    NAME_PF("name_pf","Gaio Giulio",false),
    FAMILY_NAME_PF("family_name_pf","Cesare",false),
    CODICE_FISCALE_PF("codiceFiscale_pf","CSRGGL44L13H501E",false),
    FISCAL_NUMBER_PF("fiscal_number_pf","TINIT-CSRGGL44L13H501E",false),
    INDIRIZZO_PF("indirizzo_pf","VIA ROMA",false),
    CIVICO_PF("numeroCivico_pf","20",false),
    LOCALITA_PF("localita_pf","MILANO",false),
    COMUNE_PF("comune_pf","MILANO",false),
    PROVINCIA_PF("provincia_pf","MI",false),
    CAP_PF("codicepostale_pf","20147",false),
    STATO_PF("stato_pf","ITALIA",false),
    OTP_PEC_PF("OTPpec_pf","45903",false),
    OTP_MAIL_PF("OTPmail_pf","10377",false),
    OTP_CELL_PF("OTPCell_pf","10111",false),

    //PERSONA GIURIDICA
    USER_PG("user_pg","DanteAlighieri",false),
    PWD_PG("pwd_pg","test",false),
    PROVIDER_PG("provider_pg","spid:test",false),
    EMAIL_PG("email_pg","provaemail@test.it",false),
    MAIL_PG("mail_pg","prova@test.it",false),
    EMAIL_PEC_PG("email_pec_pg", "pec@pec.pagopa.it",false),
    PEC_ERRORE_PG("pec_errore_pg","testpagopa2@@pnpagopa.postecert.local",false),
    PEC_PG("pec_pg", "prova@pec.it",false),
    RAGIONE_SOCIALE_PG("ragione_sociale_pg", "Convivio Spa",false),
    CODICE_FISCALE_PG("codice_fiscale_pg", "27957814470",false),
    FISCAL_NUMBER_PG("fiscal_number_pg", "TINIT-27957814470",false),
    INDIRIZZO_PG ("indirizzo_pg","VIA ROMA",false),
    NUMERO_CIVICO_PG ("numero_civico_pg","20",false),
    LOCALITA_PG ("localita_pg","MILANO",false),
    COMUNE_PG ("comune_pg","MILANO",false),
    PROVINCIA_PG("provincia_pg", "MI",false),
    CODICE_POSTALE_PG ("codice_postale_pg","20147",false),
    STATO_PG ("stato_pg","ITALIA",false),
    CODICE_IUN_PG("codice_iun_pg", "NZQX-DHLD-LAMU-202308-J-1",false),
    CELLULARE_PG("cellulare_pg", "333456789",false),
    BEARER_TOKEN_PG ("bearer_token_pg","Bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCIsImtpZCI6ImU0ZjJhYTIyLTE5MDctNDIyNC1iYWViLTExYzZiY2RkZDNkYSJ9.eyJpYXQiOjE3MDczMDIwMzgsImV4cCI6MTkwMDIyMzkxNywidWlkIjoiZTQ5MGYwMmUtOTQyOS00YjM4LWJiMTEtZGRiOGE1NjFmYjYyIiwiaXNzIjoiaHR0cHM6Ly93ZWJhcGkudGVzdC5ub3RpZmljaGVkaWdpdGFsaS5pdCIsImF1ZCI6IndlYmFwaS50ZXN0Lm5vdGlmaWNoZWRpZ2l0YWxpLml0IiwianRpIjoiMmVlYmY5ZTMtYTBkYS00NTEzLTkxMWQtNjMyMjBjODE2NTIyIiwib3JnYW5pemF0aW9uIjp7ImlkIjoiZDBmNTJjN2QtNzZkNS00NTIwLTg5NzEtZWRmZmViNWI0NmQ1Iiwicm9sZSI6InBnLWFkbWluIiwiZmlzY2FsX2NvZGUiOiIyNzk1NzgxNDQ3MCJ9fQ.n9ohCtZlbMXqD95Ini_-dDFRtG5Udc7a8CXz8dvqAs3Wn_FG-dfMi79LN25PULkYjv6LaygpGPELNg1dDmyg9HY4uaqdkwQGeoseZIA3V3jjMzeeWM1K7lTFxhhsPjanl2z4jgqkFLIbhrD_qg0UeKDun_oa5_W6Kk3miTh6wC7XgT19jPJWI68P5e8J8c7M2y4IzKrTG8da1hT_g41o9JUR2SC7zxKhMwf-FzGGb41ct90rTLP-9ngxjF4VvoOOJTL6g0pb0qs-Wep0vS-U_zM-H4qjEvScGCX136FKwZP75Er-M_hDGZeamqIg-bIOA9mASgtyIxVy1gLPXEGsPw",false),
    OTP_PEC_PG ("otp_pec_pg","83583",false),
    OTP_MAIL_PG("otp_mail_pg", "38630",false),
    OTP_CELL_PG("otp_mail_pg", "00000",false),

    //PERSONA GIURIDICA 1
    USER_PG_1("user_pg_1", "DanteAlighieri",false),
    PWD_PG_1("pwd_pg_1" ,"test",false),
    PROVIDER_PG_1("provider_pg_1", "spid:test",false),
    EMAIL_PEC_PG_1("email_pec_pg_1", "DanteAlighieri@paradiso.it",false),
    RAGIONE_SOCIALE_PG_1("ragione_sociale_pg_1", "Convivio Spa",false),
    CODICE_FISCALE_PG_1("codice_fiscale_pg_1", "27957814470",false),
    FISCAL_NUMBER_PG_1("fiscal_number_pg_1", "TINIT-12666810299",false),
    INDIRIZZO_PG_1("indirizzo_pg_1", "VIA ROMA",false),
    NUMERO_CIVICO_PG_1("numero_civico_pg_1", "20",false),
    LOCALITA_PG_1("localita_pg_1", "MILANO",false),
    COMUNE_PG_1("comune_pg_1","MILANO",false),
    PROVINCIA_PG_1("provincia_pg_1", "MI",false),
    CODICE_POSTALE_PG_1("codice_postale_pg_1", "20147",false),
    STATO_PG_1("stato_pg_1", "ITALIA",false),
    CODICE_IUN_PG_1("codice_iun_pg_1", "NZQX-DHLD-LAMU-202308-J-1",false),

    //PERSONA GIURIDICA ERRORE
    CODICE_FISCALE_PG_ERRORE("codice_fiscale_pg_errore", "CCRMC06A03A433H",false),
    EMAIL_PEC_PG_ERRORE("email_pec_pg_errore", "provatest2.spqe",false),

    //PERSONA FISICA PEC
    EMAIL_PF_PEC("email_pf_pec","testpagopa2@pnpagopa.postecert.local",false),
    NAME_PF_PEC("name_pf_pec","Galileo",false),
    FAMILYNAME_PF_PEC("familyName_pf_pec","password123",false),
    FISCALCODE_PF_PEC("fiscalcode_pf_pec","GLLGLL64B15G702I",false),
    FISCALNUMBER_PF_PEC("fiscalnumber_pf_pec","TINIT-GLLGLL64B15G702I",false),
    INDIRIZZO_PF_PEC("indirizzo_pf_pec","VIA ROMA",false),
    NUMEROCIVICO_PF_PEC("numerocivico_pf_pec","20",false),
    LOCALITA_PF_PEC("localita_pf_pec","MILANO",false),
    COMUNE_PF_PEC("comune_pf_pec","MILANO",false),
    PROVINCIA_PF_PEC("provincia_pf_pec","MI",false),
    CAP_PF_PEC("cap_pf_pec","20147",false),
    STATO_PF_PEC("stato_pf_pec","ITALIA",false),

    //DATI NOTIFICA
    NUMERO_PROTOCOLLO_DN("numero_protocollo_dn","TA-FFSMRC-20241104-3015",false),
    OGGETTO_DELLA_NOTIFICA_DN("oggetto_della_notifica_dn","Pagamento rata IMU",false),
    DESCRIZIONE_DN("descrizione_dn","PAGAMENTO RATA IMU",false),
    GRUPPO_TEST_DN("gruppo_test_dn","test-TA-FE-TEST",false),
    GRUPPO_DEV_DN("gruppo_dev_dn","GruppoTest",false),
    GRUPPO_UAT_DN("gruppo_uat_dn","Gruppo1",false),
    //    CODICE_TASSONOMETRICO_DN("codice_tassonometrico_dn","123456A",false),
    CODICE_TASSONOMETRICO_DN("codice_tassonometrico_dn","100105P",false),
    NOME_DOCUMENTO_NOTIFICA_DN("nome_documento_notifica_dn","RATA SCADUTA IMU",false),
    CODICE_IUN_DN("codice_iun_dn","notifica multi destinatario con020",false),

    OGGETTO_DELLA_NOTIFICA_FR_DN("oggetto_della_notifica_dn","FR Pagamento rata IMU",false),
    DESCRIZIONE_FR_DN("descrizione_dn","FR PAGAMENTO RATA IMU",false),
    OGGETTO_DELLA_NOTIFICA_DE_DN("oggetto_della_notifica_dn","DE Pagamento rata IMU",false),
    DESCRIZIONE_DE_DN("descrizione_dn","DE PAGAMENTO RATA IMU",false),
    OGGETTO_DELLA_NOTIFICA_SL_DN("oggetto_della_notifica_dn","SL Pagamento rata IMU",false),
    DESCRIZIONE_SL_DN("descrizione_dn","SL PAGAMENTO RATA IMU",false),

    //DATI NOTIFICA ERRORE
    OGGETTO_DELLA_NOTIFICA_ERRORE("oggetto_della_notifica_err","IMU",false),
    CODICE_TASSONOMETRICO_ERRORE("codiceTassonometrico_err","123456",false),




    ;


    public final String key;
    private final String defaultValue;
    private final boolean addCurrentTime;
    private static final ObjectMapper mapper = new ObjectMapper();

    private static final String NULL_VALUE = "NULL";
    public static final String EXCLUDE_VALUE = "NO";
    private static final Integer NOTICE_CODE_LENGTH = 18;


    DataPopulationValue(String key, String defaultValue, boolean addCurrentTime){
        this.key = key;
        this.defaultValue = defaultValue;
        this.addCurrentTime = addCurrentTime;
    }


    public static String getDefaultValue(String key) {
        DataPopulationValue notificationValue =
                Arrays.stream(DataPopulationValue.values()).filter(value -> value.key.equals(key)).findFirst().orElse(null);
        System.out.println(notificationValue.defaultValue);
        return (notificationValue == null ? null : (notificationValue.addCurrentTime? (notificationValue.defaultValue + generateRandomNumber() ) : notificationValue.defaultValue));
        /*
        String number = threadNumber.length() < 2 ? "0"+threadNumber: threadNumber.substring(0, 2);
        return (notificationValue == null ? null : (notificationValue.addCurrentTime? (notificationValue.defaultValue + (""+String.format("302"+number+"%13d",System.currentTimeMillis()))) : notificationValue.defaultValue));
         */
    }

    public static String  generateRandomNumber(){
        String threadNumber = (Thread.currentThread().getId()+"");
        String numberOfThread = threadNumber.length() < 2 ? "0"+threadNumber: threadNumber.substring(0, 2);
        String timeNano = System.nanoTime()+"";
        String randomClassePagamento = new Random().nextInt(14)+"";
        randomClassePagamento = randomClassePagamento.length() < 2 ? "0"+randomClassePagamento : randomClassePagamento;
        String finalNumber = "" + String.format("302" +randomClassePagamento + numberOfThread + timeNano.substring(0, timeNano.length()-4));
        // String finalNumber = "" + String.format("30210" +randomClassePagamento + numberOfThread + timeNano.substring(0, timeNano.length()-6));
        if(finalNumber.length() > NOTICE_CODE_LENGTH){
            finalNumber = finalNumber.substring(0,NOTICE_CODE_LENGTH);
        }else{
            int remainingLength = NOTICE_CODE_LENGTH - finalNumber.length();
            String paddingString = String.valueOf(new Random().nextInt(9)).repeat(remainingLength);
            finalNumber = finalNumber + paddingString;
        }
        return finalNumber;
    }

    public static String getValue(Map<String, String> data, String key){
        if(data.containsKey(key)){
            /* TEST
            if(data.get(key).equals(EXCLUDE_VALUE)){
                return EXCLUDE_VALUE;
            }
             */
            return data.get(key).equals(NULL_VALUE) ? null : (data.get(key).contains("_CHAR")? getCharSeq(data.get(key)):data.get(key));
        }else{
            return getDefaultValue(key);
        }
    }

    public static String getCharSeq(String request){
        StringBuilder result = new StringBuilder();
        int number = Integer.parseInt(request.substring(0,request.indexOf("_")));
        result.append("a".repeat(Math.max(0, number)));
        return result.toString();
    }

    public static <T> T getCastedDefaultValue(String key) {
        DataPopulationValue notificationValue =
                Arrays.stream(DataPopulationValue.values()).filter(value -> value.key.equals(key)).findFirst().orElse(null);
        return notificationValue == null ? null : (T) notificationValue.defaultValue;
    }

    public static <T> T getObjValue(Class<T> clazz, Map<String, String> data, String key) throws JsonProcessingException {
        if(data.containsKey(key)){
            T map = mapper.readValue(data.get(key), clazz);
            return data.get(key).equals(NULL_VALUE) ? null : map;
        }else{
            return getCastedDefaultValue(key);
        }
    }

    public static <T> List<T> getListValue(Class<T> clazz, Map<String, String> data, String key) throws JsonProcessingException {
        if(data.containsKey(key)){
            JavaType type = mapper.getTypeFactory().constructParametricType(List.class, clazz);
            List<T> map = mapper.readValue(data.get(key), type);
            return data.get(key).equals(NULL_VALUE) ? null : map;
        }else{
            return getCastedDefaultValue(key);
        }
    }
}
