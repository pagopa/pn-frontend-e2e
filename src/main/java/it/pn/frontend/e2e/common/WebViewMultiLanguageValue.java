package it.pn.frontend.e2e.common;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Random;

public enum WebViewMultiLanguageValue {


    CLICK_ACCEDI_BUTTON_IT("Accedi","Accedi",false),
    CLICK_ACCEDI_BUTTON_EN("Login","Login",false),
    CLICK_ACCEDI_BUTTON_FR("Se connecter","Se connecter",false),
    CLICK_ACCEDI_BUTTON_DE("Anmelden","Anmelden",false),
    CLICK_ACCEDI_BUTTON_SL("Prijava","Prijava",false),


    WAIT_LOAD_SELEZIONA_IMPRESA_PAGE_IT("Le tue imprese su SEND","Le tue imprese su SEND",false),
    WAIT_LOAD_SELEZIONA_IMPRESA_PAGE_EN("Select your company","Select your company",false),
    WAIT_LOAD_SELEZIONA_IMPRESA_PAGE_FR("Sélectionnez votre entreprise","Sélectionnez votre entreprise",false),
    WAIT_LOAD_SELEZIONA_IMPRESA_PAGE_DE("Wähle dein Unternehmen","Wähle dein Unternehmen",false),
    WAIT_LOAD_SELEZIONA_IMPRESA_PAGE_SL("Izberite svoje podjetje","Izberite svoje podjetje",false),

    WAIT_LOAD_ACCEDI_AREA_RISERVATA_PG_PAGE_IT("Come vuoi accedere","Come vuoi accedere",false),
    WAIT_LOAD_ACCEDI_AREA_RISERVATA_PG_PAGE_EN("How do you want to log in","How do you want to log in",false),
    WAIT_LOAD_ACCEDI_AREA_RISERVATA_PG_PAGE_FR("Comment voulez-vous y accéde","Comment voulez-vous y accéde",false),
    WAIT_LOAD_ACCEDI_AREA_RISERVATA_PG_PAGE_DE("Wie möchtest du dich anmelden","Wie möchtest du dich anmelden",false),
    WAIT_LOAD_ACCEDI_AREA_RISERVATA_PG_PAGE_SL("Kako se želite prijaviti","Kako se želite prijaviti",false),

    ;


    public final String key;
    private final String defaultValue;
    private final boolean addCurrentTime;
    private static final ObjectMapper mapper = new ObjectMapper();

    private static final String NULL_VALUE = "NULL";
    public static final String EXCLUDE_VALUE = "NO";
    private static final Integer NOTICE_CODE_LENGTH = 18;


    WebViewMultiLanguageValue(String key, String defaultValue, boolean addCurrentTime){
        this.key = key;
        this.defaultValue = defaultValue;
        this.addCurrentTime = addCurrentTime;
    }


    public static String getDefaultValue(String key) {
        WebViewMultiLanguageValue notificationValue =
                Arrays.stream(WebViewMultiLanguageValue.values()).filter(value -> value.key.equals(key)).findFirst().orElse(null);
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
        WebViewMultiLanguageValue notificationValue =
                Arrays.stream(WebViewMultiLanguageValue.values()).filter(value -> value.key.equals(key)).findFirst().orElse(null);
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
