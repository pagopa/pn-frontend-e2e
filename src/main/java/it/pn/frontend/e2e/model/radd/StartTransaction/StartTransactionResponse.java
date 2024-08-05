package it.pn.frontend.e2e.model.radd.StartTransaction;

import it.pn.frontend.e2e.model.radd.DownloadUrl;
import lombok.Data;

import java.util.List;

@Data
public class StartTransactionResponse {
    private StartTransactionResponseStatus startTransactionResponseStatus;
    private List<DownloadUrl> downloadUrlList;

    public StartTransactionResponse(StartTransactionResponseStatus startTransactionResponseStatus, List<DownloadUrl> downloadUrlList) {
        this.startTransactionResponseStatus = startTransactionResponseStatus;
        this.downloadUrlList = downloadUrlList;
    }
}
