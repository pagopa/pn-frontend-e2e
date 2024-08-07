package it.pn.frontend.e2e.model.payments;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Payment {

    private PagoPa pagoPa;

    public Payment(){
        pagoPa = new PagoPa();
    }

    public Payment(PagoPa pagoPa){
        this.pagoPa = pagoPa;
    }

}
