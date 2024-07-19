package it.pn.frontend.e2e.model.address;

import it.pn.frontend.e2e.model.enums.DigitalDomicileTypeEnum;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DigitalAddress {
    private String addressType;
    private String recipientId;
    private String senderId;
    private String senderName;
    private DigitalDomicileTypeEnum channelType;
    private String value;
    private String requestId;
    private Boolean pecValid;
    private Boolean codeValid;
}
