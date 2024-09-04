package it.pn.frontend.e2e.stepDefinitions.common;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Random;

public enum NotificationValue {

    //DATA NOTIFICA
    SUBJECT("numeroProtocollo","TA-FFSMRC-20240606-5985",true),
    DESCRIPTION("oggettoDellaNotifica","Pagamento rata IMU",false),
    GROUP_TEST("gruppoTest","test-TA-FE-TEST",false),
    GROUP_DEV("gruppoDev","GruppoTest",false),
    CODE_TASS("codiceTassonometrico","123456A",false),
    NAME_DOC_NOTIF("nomeDocumentoNotifica","RATA SCADUTA IMU",false),
    COD_IUN("codiceIUN","Pagamento rata IMU",false),

    //DATA NOTIFICA ERRORE
    SUBJECT_ERR("oggettoDellaNotifica","IMU",false),
    CODE_TASS_ERR("oggettoDellaNotifica","Pagamento rata IMU",false),

    //DELEGATO PF NOTIFICA
    URL_DELEGATO_PF("url_delegato_pf","https://cittadini.dev.notifichedigitali.it/",false),
    USER_DELEGATO_PF("user_delegato_pf","lucrezia",false),
    PWD_DELEGATO_PF("pwd_delegato_pf","password123",false),
    PROVIDER_DELEGATO_PF("provider_delegato_pf","spid:test",false),
    NAME_DELEGATO_PF("name_delegato_pf","Lucrezia",false),
    FAMILY_DELEGATO_PF("familyName_delegato_pf","Borgia",false),
    FISCAL_CODE_DELEGATO_PF("codiceFiscale_delegato_pf","BRGLRZ80D58H501Q",false),
    FISCAL_NUMBER_DELEGATO_PF("fiscalNumber_delegato_pf","TINIT-BRGLRZ80D58H501Q",false),
    STATE_DELEGATO_PF("stato_delegato_pf","ITALIA",false),

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
    NAME_DELEGA("nome","Lucrezia",false),
    SURNAME_DELEGA("cognome","Borgia",false),
    FISCAL_CODE_DELEGA("codiceFiscale","BRGLRZ80D58H501Q",false),
    ENTE("ente","Comune di Verona",false),
    CODE_DELEGA("codiceDelega","42420",false),
    RAG_SOC_DELEGA("ragioneSociale","Lucrezia Borgia",false),

    //NUOVA DELEGA ERR PF
    NAME_DELEGA_ERR("nome","Gaio Giulio",false),
    SURNAME_DELEGA_ERR("cognome","Cesare",false),
    FISCAL_CODE_DELEGA_ERR("codiceFiscale","CSRGGL44L13H501E",false),
    ENTE_ERR("ente","Comune di Palermo",false),
    CODE_DELEGA_ERR("codiceDelega","22611",false),

    //NUOVA DELEGA PG
    FISCAL_CODE_DELEGA_PG("codiceFiscale","LELPTR04A01C352E",false),
    ENTE_PG("ente","Comune di Palermo",false),
    CODE_DELEGA_PG("codiceDelega","27430",false),
    RAG_SOC_DELEGA_PG("ragioneSociale","Le Epistolae srl",false),

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
    EMAIL_PF("email","provaemail@test.it",false);


    public final String key;
    private final String defaultValue;
    private final boolean addCurrentTime;
    private static final ObjectMapper mapper = new ObjectMapper();

    private static final String NULL_VALUE = "NULL";
    public static final String EXCLUDE_VALUE = "NO";
    private static final Integer NOTICE_CODE_LENGTH = 18;


    NotificationValue(String key, String defaultValue, boolean addCurrentTime){
        this.key = key;
        this.defaultValue = defaultValue;
        this.addCurrentTime = addCurrentTime;
    }


    public static String getDefaultValue(String key) {
        NotificationValue notificationValue =
                Arrays.stream(NotificationValue.values()).filter(value -> value.key.equals(key)).findFirst().orElse(null);
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
        NotificationValue notificationValue =
                Arrays.stream(NotificationValue.values()).filter(value -> value.key.equals(key)).findFirst().orElse(null);
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
