package it.pn.frontend.e2e.model.documents;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Data;

import javax.annotation.processing.Generated;
import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2024-06-07T16:01:53.520944300+02:00[Europe/Rome]")
@Data
public class PreLoadResponse {
    public static final String JSON_PROPERTY_PRELOAD_IDX = "preloadIdx";
    private String preloadIdx;

    public static final String JSON_PROPERTY_SECRET = "secret";
    private String secret;

    public enum HttpMethodEnum {
        POST("POST"),

        PUT("PUT");

        private String value;

        HttpMethodEnum(String value) {
            this.value = value;
        }

        @JsonValue
        public String getValue() {
            return value;
        }

        @Override
        public String toString() {
            return String.valueOf(value);
        }

        @JsonCreator
        public static HttpMethodEnum fromValue(String value) {
            for (HttpMethodEnum b : HttpMethodEnum.values()) {
                if (b.value.equals(value)) {
                    return b;
                }
            }
            throw new IllegalArgumentException("Unexpected value '" + value + "'");
        }
    }

    public static final String JSON_PROPERTY_HTTP_METHOD = "httpMethod";
    private HttpMethodEnum httpMethod;

    public static final String JSON_PROPERTY_URL = "url";
    private String url;

    public static final String JSON_PROPERTY_KEY = "key";
    private String key;

    public PreLoadResponse() {
    }

    public PreLoadResponse preloadIdx(String preloadIdx) {

        this.preloadIdx = preloadIdx;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PreLoadResponse preLoadResponse = (PreLoadResponse) o;
        return Objects.equals(this.preloadIdx, preLoadResponse.preloadIdx) &&
                Objects.equals(this.secret, preLoadResponse.secret) &&
                Objects.equals(this.httpMethod, preLoadResponse.httpMethod) &&
                Objects.equals(this.url, preLoadResponse.url) &&
                Objects.equals(this.key, preLoadResponse.key);
    }

    @Override
    public int hashCode() {
        return Objects.hash(preloadIdx, secret, httpMethod, url, key);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class PreLoadResponse {\n");
        sb.append("    preloadIdx: ").append(toIndentedString(preloadIdx)).append("\n");
        sb.append("    secret: ").append(toIndentedString(secret)).append("\n");
        sb.append("    httpMethod: ").append(toIndentedString(httpMethod)).append("\n");
        sb.append("    url: ").append(toIndentedString(url)).append("\n");
        sb.append("    key: ").append(toIndentedString(key)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    private String toIndentedString(Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}
