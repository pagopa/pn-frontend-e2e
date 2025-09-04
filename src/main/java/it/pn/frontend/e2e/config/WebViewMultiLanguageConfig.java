package it.pn.frontend.e2e.config;

import it.pn.frontend.e2e.common.WebViewMultiLanguageValue;
import it.pn.frontend.e2e.model.webViewMultiLanguage.ButtonLanguage;
import it.pn.frontend.e2e.model.webViewMultiLanguage.WaitLoadAccediAreaRiservataPgLanguage;
import it.pn.frontend.e2e.model.webViewMultiLanguage.WaitLoadSelezionaImpresaLanguage;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Getter
@Configuration
public class WebViewMultiLanguageConfig {

    @Setter
    private ButtonLanguage buttonLanguage;

    @Setter
    private WaitLoadSelezionaImpresaLanguage waitLoadSelezionaImpresaLanguage;
    @Setter
    private WaitLoadAccediAreaRiservataPgLanguage waitLoadAccediAreaRiservataPgLanguage;

    @Bean
    public ButtonLanguage buttonLanguage() {
        buttonLanguage = new ButtonLanguage();
        buttonLanguage.setClickAccediButtonIt(WebViewMultiLanguageValue.getDefaultValue(WebViewMultiLanguageValue.CLICK_ACCEDI_BUTTON_IT.key));
        buttonLanguage.setClickAccediButtonEn(WebViewMultiLanguageValue.getDefaultValue(WebViewMultiLanguageValue.CLICK_ACCEDI_BUTTON_EN.key));
        buttonLanguage.setClickAccediButtonFr(WebViewMultiLanguageValue.getDefaultValue(WebViewMultiLanguageValue.CLICK_ACCEDI_BUTTON_FR.key));
        buttonLanguage.setClickAccediButtonDe(WebViewMultiLanguageValue.getDefaultValue(WebViewMultiLanguageValue.CLICK_ACCEDI_BUTTON_DE.key));
        buttonLanguage.setClickAccediButtonSl(WebViewMultiLanguageValue.getDefaultValue(WebViewMultiLanguageValue.CLICK_ACCEDI_BUTTON_SL.key));
        return buttonLanguage;

    }

    @Bean
    public WaitLoadSelezionaImpresaLanguage waitLoadSelezionaImpresaLanguage() {
        waitLoadSelezionaImpresaLanguage = new WaitLoadSelezionaImpresaLanguage();
        waitLoadSelezionaImpresaLanguage.setWaitLoadSelezionaImpresaPageIt(WebViewMultiLanguageValue.getDefaultValue(WebViewMultiLanguageValue.WAIT_LOAD_SELEZIONA_IMPRESA_PAGE_IT.key));
        waitLoadSelezionaImpresaLanguage.setWaitLoadSelezionaImpresaPageEn(WebViewMultiLanguageValue.getDefaultValue(WebViewMultiLanguageValue.WAIT_LOAD_SELEZIONA_IMPRESA_PAGE_EN.key));
        waitLoadSelezionaImpresaLanguage.setWaitLoadSelezionaImpresaPageFr(WebViewMultiLanguageValue.getDefaultValue(WebViewMultiLanguageValue.WAIT_LOAD_SELEZIONA_IMPRESA_PAGE_FR.key));
        waitLoadSelezionaImpresaLanguage.setWaitLoadSelezionaImpresaPageDe(WebViewMultiLanguageValue.getDefaultValue(WebViewMultiLanguageValue.WAIT_LOAD_SELEZIONA_IMPRESA_PAGE_DE.key));
        waitLoadSelezionaImpresaLanguage.setWaitLoadSelezionaImpresaPageSl(WebViewMultiLanguageValue.getDefaultValue(WebViewMultiLanguageValue.WAIT_LOAD_SELEZIONA_IMPRESA_PAGE_SL.key));
        return waitLoadSelezionaImpresaLanguage;
    }

    @Bean
    public WaitLoadAccediAreaRiservataPgLanguage waitLoadAccediAreaRiservataPgLanguage() {
        waitLoadAccediAreaRiservataPgLanguage = new WaitLoadAccediAreaRiservataPgLanguage();

        waitLoadAccediAreaRiservataPgLanguage.setWaitLoadAccediAreaRiservataPGPageIt(WebViewMultiLanguageValue.getDefaultValue(WebViewMultiLanguageValue.WAIT_LOAD_ACCEDI_AREA_RISERVATA_PG_PAGE_IT.key));
        waitLoadAccediAreaRiservataPgLanguage.setWaitLoadAccediAreaRiservataPGPageEn(WebViewMultiLanguageValue.getDefaultValue(WebViewMultiLanguageValue.WAIT_LOAD_ACCEDI_AREA_RISERVATA_PG_PAGE_EN.key));
        waitLoadAccediAreaRiservataPgLanguage.setWaitLoadAccediAreaRiservataPGPageFr(WebViewMultiLanguageValue.getDefaultValue(WebViewMultiLanguageValue.WAIT_LOAD_ACCEDI_AREA_RISERVATA_PG_PAGE_FR.key));
        waitLoadAccediAreaRiservataPgLanguage.setWaitLoadAccediAreaRiservataPGPageDe(WebViewMultiLanguageValue.getDefaultValue(WebViewMultiLanguageValue.WAIT_LOAD_ACCEDI_AREA_RISERVATA_PG_PAGE_DE.key));
        waitLoadAccediAreaRiservataPgLanguage.setWaitLoadAccediAreaRiservataPGPageSl(WebViewMultiLanguageValue.getDefaultValue(WebViewMultiLanguageValue.WAIT_LOAD_ACCEDI_AREA_RISERVATA_PG_PAGE_SL.key));
        return waitLoadAccediAreaRiservataPgLanguage;
    }


}
