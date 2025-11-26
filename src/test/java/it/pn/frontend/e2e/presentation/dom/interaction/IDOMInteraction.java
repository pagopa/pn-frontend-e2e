package it.pn.frontend.e2e.presentation.dom.interaction;

public interface IDOMInteraction {

    /** Click su un elemento identificato da un selettore CSS/XPath */
    void click(String selector);

    /** Scrive del testo in un campo (input, textarea…) */
    void type(String selector, String text);

    /** Cancella il contenuto di un campo */
    void clear(String selector);

    /** Seleziona un’opzione in una select HTML */
    void selectByValue(String selector, String value);

    /** Seleziona un’opzione in base al testo visibile */
    void selectByVisibleText(String selector, String text);

    /** Simula lo scroll fino a un elemento */
    void scrollTo(String selector);

    /** Simula la pressione di un tasto */
    void pressKey(String selector, CharSequence key);

    /** Simula la navigazione verso un URL */
    void navigateTo(String url);

    /** Esegue un click tramite JavaScript (fallback per elementi non cliccabili) */
    void jsClick(String selector);

    /** Esegue un input di testo tramite JavaScript (fallback per casi problematici) */
    void jsSetValue(String selector, String value);
}

