package it.pn.frontend.e2e.section.mittente;

import it.pn.frontend.e2e.common.BasePage;
import org.openqa.selenium.WebDriver;

public class DestinatarioPASection extends BasePage {
    public DestinatarioPASection(WebDriver driver) {
        super(driver);
    }

    public String ricercaInformazione(String[] dati, int posizioneDestinatario ){
        String datoDestianario = dati[posizioneDestinatario];
        if (posizioneDestinatario == 0){
            datoDestianario = datoDestianario.replace("[","");
        }else if (posizioneDestinatario == 4){
            datoDestianario = datoDestianario.replace("]","");
        }
        return datoDestianario;
    }
}
