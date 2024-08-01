package it.pn.frontend.e2e.model.payments;

import it.pn.frontend.e2e.model.documents.MetadataAttachment;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class F24 {
    private String title;
    private Boolean applyCost;
    private MetadataAttachment metadataAttachment;


    public F24(){
        title = "F24";
        applyCost = false;
       metadataAttachment = new MetadataAttachment();
    }

    public F24(String title, Boolean applyCost, MetadataAttachment metadataAttachment){
        this.title = title;
        this.applyCost = applyCost;
        this.metadataAttachment = metadataAttachment;
    }

}
