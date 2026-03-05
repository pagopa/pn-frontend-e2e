package it.pn.frontend.e2e.parameter_type;

import io.cucumber.java.ParameterType;
import it.frontend.e2e.framework.web.model.WebLocation;
import it.pn.frontend.e2e.model.PageInfo;
import it.pn.frontend.e2e.steps.send.login.page.OneIdPage;
import it.pn.frontend.e2e.steps.send.mittenti.Dashboard;
import org.springframework.beans.factory.annotation.Value;

public class PageType {

    @Value("${url.mittente}")
    private String mittentiBaseUrl;

    private String token = "eyJraWQiOiJqd3QtZXhjaGFuZ2VfZWE6NDg6NTI6ZTQ6YWU6OGY6MzA6YjU6YWQ6M2M6ZDI6MDU6NzQ6Nzk6Yzk6ZWYiLCJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJmYW1pbHlfbmFtZSI6IkJhcm9uZSIsImZpc2NhbF9udW1iZXIiOiJCUk5CQ0g5MUw0OUg4MjJFIiwibmFtZSI6IkJhY2NoaXNpbyIsInNwaWRfbGV2ZWwiOiJodHRwczovL3d3dy5zcGlkLmdvdi5pdC9TcGlkTDIiLCJmcm9tX2FhIjpmYWxzZSwidWlkIjoiMjEzNGQ5MmItNWQxYi00OWI1LTlhNmUtNDI4M2Y0ODc5YmJmIiwibGV2ZWwiOiJMMiIsImlhdCI6MTcwMDIyMjU0NiwiZXhwIjoxOTAwMjIyNTYxLCJhdWQiOiJzZWxmY2FyZS50ZXN0Lm5vdGlmaWNoZWRpZ2l0YWxpLml0IiwiaXNzIjoiaHR0cHM6Ly91YXQuc2VsZmNhcmUucGFnb3BhLml0IiwianRpIjoiZTA2NGI1MmYtMjUyNC00MDEyLWI5N2QtZjZlNmYyZDYzMmMyIiwiZW1haWwiOiJyc2FubmFAZ21haWwuY29tIiwib3JnYW5pemF0aW9uIjp7ImlkIjoiYTk1ZGFjZTQtNGE0Ny00MTQ5LWE4MTQtMGU2NjkxMTNjZTQwIiwibmFtZSI6IkNvbXVuZSBkaSBWZXJvbmEiLCJyb2xlcyI6W3sicGFydHlSb2xlIjoiTUFOQUdFUiIsInJvbGUiOiJhZG1pbiJ9XSwic3ViVW5pdENvZGUiOm51bGwsInN1YlVuaXRUeXBlIjpudWxsLCJhb29QYXJlbnQiOm51bGwsInBhcmVudERlc2NyaXB0aW9uIjpudWxsLCJyb290UGFyZW50Ijp7ImlkIjpudWxsLCJkZXNjcmlwdGlvbiI6bnVsbH0sImZpc2NhbF9jb2RlIjoiMDAyMTUxNTAyMzYiLCJpcGFDb2RlIjoiY19sNzgxIn0sImRlc2lyZWRfZXhwIjoxOTAwMjIyNTYxfQ.r4QCr_qE5JZMlWiBmpKDqA9D6iCog0xcodO7g-NzTklZh5lxlrw_FVlqxQi7WA5BdYsPIlvqytGLBCQxQfiTKvuO-ieK_L1W4QxK5R_3DqNWwB1UuNssIWV1nhta2chKZhmZpZisI6MhVTmqBx18Eyh1iEoO1nbM6lfokRNOz2a4gvYqCKkmC58hbWCYFcpIqvxDyEed5fLr84NvcaQxopydCIBIMEcthyHWrWuO7AplFMd-6FOtMm3k4PMSEj6586T0CfZ-IVAqXZw-tP_A1ubygfEhrQGaVNPufkk5eAL_oFtgKb2VaRoyf4tyv0zCogNC9vwnMQZaBuzXiZIHsg";


    @ParameterType("LoginPage|Dashboard")
    public PageInfo page(String page) {
        return switch (page) {
            case "LoginPage" -> new PageInfo(WebLocation.of(null), OneIdPage.class);
            case "Dashboard" -> new PageInfo(WebLocation.of(mittentiBaseUrl+"#selfCareToken="+token), Dashboard.class);
            default -> throw new IllegalArgumentException("Invalid page");
        };
    }
}
