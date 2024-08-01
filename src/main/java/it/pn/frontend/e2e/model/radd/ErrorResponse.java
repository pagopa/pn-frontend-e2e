package it.pn.frontend.e2e.model.radd;

import io.opentelemetry.api.trace.StatusCode;
import lombok.Data;

import java.util.List;
@Data
public class ErrorResponse {
    private String type;
    private StatusCode status;
    private String title;
    private String detail;
    private String traceId;
    private String timestamp;
    private List<String> errors;

    public ErrorResponse(String type, StatusCode status, String title, String detail, String traceId, String timestamp, List<String> errors) {
        this.type = type;
        this.status = status;
        this.title = title;
        this.detail = detail;
        this.traceId = traceId;
        this.timestamp = timestamp;
        this.errors = errors;
    }
}
