package it.pn.frontend.e2e.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Payment {

    private PagoPa pagoPa;
    private F24 f24;

    public Payment(){
        this.pagoPa = new PagoPa();
        this.f24 = new F24();
    }

    public Payment(PagoPa pagoPa, F24 f24){
        this.pagoPa = pagoPa;
        this.f24 = f24;
    }

}
