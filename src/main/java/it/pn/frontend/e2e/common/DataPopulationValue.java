package it.pn.frontend.e2e.common;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Random;

public enum DataPopulationValue {

    //DATA NOTIFICA PG
    OGGETTO_DELLA_NOTIFICA("oggettoDellaNotifica","Pagamento rata IMU",true),
    CODICE_IUN("codiceIUN","EGNM-DPAR-VTLR-202401-T-1",false),


    //DATA NOTIFICA ERRORE
    SUBJECT_ERR("oggettoDellaNotifica_err","IMU",false),
    CODE_TASS_ERR("codiceTassonometrico_err","Pagamento rata IMU",false),

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
    ENV("ambiente_mittente","dev",false),
    FISCAL_CODE_MITTENTE("codiceFiscale_mittente","00189800204",false),
    COD_AVVISO("codiceAvviso","302047770009990299",false),
    API_KEY_TEST("codiceApiKeyTEST","2b3d47f4-44c1-4b49-b6ef-54dc1c531311",false),
    API_KEY_DEV("codiceApiKeyDEV","a9f0508d-c344-4347-807f-343bc8210996",false),

    //NUOVA DELEGA PF
    NAME_DELEGA("nome_delega_pf","Lucrezia",false),
    SURNAME_DELEGA("cognome_delega_pf","Borgia",false),
    FISCAL_CODE_DELEGA("codiceFiscale_delega_pf","BRGLRZ80D58H501Q",false),
    ENTE_DELEGA_PF("ente_delega_pf","Comune di Verona",false),
    CODE_DELEGA("codiceDelega_pf","42420",false),
    RAG_SOC_DELEGA("ragioneSociale_delega_pf","Lucrezia Borgia",false),

    //NUOVA DELEGA ERR PF
    NAME_DELEGA_ERR("nome_delega_err_pf","Gaio Giulio",false),
    SURNAME_DELEGA_ERR("cognome_delega_err_pf","Cesare",false),
    FISCAL_CODE_DELEGA_ERR("codiceFiscale_delega_err_pf","CSRGGL44L13H501E",false),
    ENTE_ERR("ente_delega_err_pf","Comune di Palermo",false),
    CODE_DELEGA_ERR("codiceDelega_delega_err_pf","22611",false),

    //NUOVA DELEGA PG
    FISCAL_CODE_DELEGA_PG("codiceFiscale_delega_pg","LELPTR04A01C352E",false),
    ENTE_PG("ente_delega_pg","Comune di Verona",false),
    CODE_DELEGA_PG("codiceDelega_delega_pg","27430",false),
    RAG_SOC_DELEGA_PG("ragioneSociale_delega_pg","Le Epistolae srl",false),

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


    //PERSONA GIURIDICA
    EMAIL_PG("email_pg","provaemail@test.it",false),
    USER_PG("user_pg","DanteAlighieri",false),
    PWD_PG("pwd_pg","test",false),
    PROVIDER_PG("provider_pg","spid:test",false),
    /* DA CONTINUARE email: "provaemail@test.it"
    mail: "prova@test.it"
    emailPec: "pec@pec.pagopa.it"
    pecErrore: "testpagopa2@@pnpagopa.postecert.local"
    pec: "prova@pec.it"
    ragioneSociale: "Convivio Spa"
    codiceFiscale: "27957814470"
    fiscalNumber: "TINIT-27957814470"
    indirizzo: "VIA ROMA"
    numeroCivico: "20"
    localita: "MILANO"
    comune: "MILANO"
    provincia: "MI"
    codicepostale: "20147"
    stato: "ITALIA"
    codiceIUN: "NZQX-DHLD-LAMU-202308-J-1"
    cellulare: "333456789"
    bearerToken: "Bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCIsImtpZCI6ImU0ZjJhYTIyLTE5MDctNDIyNC1iYWViLTExYzZiY2RkZDNkYSJ9.eyJpYXQiOjE3MDczMDIwMzgsImV4cCI6MTkwMDIyMzkxNywidWlkIjoiZTQ5MGYwMmUtOTQyOS00YjM4LWJiMTEtZGRiOGE1NjFmYjYyIiwiaXNzIjoiaHR0cHM6Ly93ZWJhcGkudGVzdC5ub3RpZmljaGVkaWdpdGFsaS5pdCIsImF1ZCI6IndlYmFwaS50ZXN0Lm5vdGlmaWNoZWRpZ2l0YWxpLml0IiwianRpIjoiMmVlYmY5ZTMtYTBkYS00NTEzLTkxMWQtNjMyMjBjODE2NTIyIiwib3JnYW5pemF0aW9uIjp7ImlkIjoiZDBmNTJjN2QtNzZkNS00NTIwLTg5NzEtZWRmZmViNWI0NmQ1Iiwicm9sZSI6InBnLWFkbWluIiwiZmlzY2FsX2NvZGUiOiIyNzk1NzgxNDQ3MCJ9fQ.n9ohCtZlbMXqD95Ini_-dDFRtG5Udc7a8CXz8dvqAs3Wn_FG-dfMi79LN25PULkYjv6LaygpGPELNg1dDmyg9HY4uaqdkwQGeoseZIA3V3jjMzeeWM1K7lTFxhhsPjanl2z4jgqkFLIbhrD_qg0UeKDun_oa5_W6Kk3miTh6wC7XgT19jPJWI68P5e8J8c7M2y4IzKrTG8da1hT_g41o9JUR2SC7zxKhMwf-FzGGb41ct90rTLP-9ngxjF4VvoOOJTL6g0pb0qs-Wep0vS-U_zM-H4qjEvScGCX136FKwZP75Er-M_hDGZeamqIg-bIOA9mASgtyIxVy1gLPXEGsPw"
    OTPpec: "83583"
    OTPmail: "38630"
*/
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
    STATO_PF_PEC("stato_pf_pec","ITALIA",false);






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
