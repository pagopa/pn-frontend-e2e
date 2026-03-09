package it.frontend.e2e.framework.web.model;

import it.frontend.e2e.framework.core.model.Selector;
import lombok.Getter;

@Getter
public class WebSelector implements Selector {

    /**
     * Enum per rappresentare i tipi di selettore web supportati
     */
    public enum SelectorType {
        XPATH("XPath");

        private final String displayName;

        SelectorType(String displayName) {
            this.displayName = displayName;
        }

        public String getDisplayName() {
            return displayName;
        }
    }

    private final String selector;
    private final SelectorType selectorType;

    /**
     * Costruisce un WebSelector analizzando automaticamente il tipo di selettore
     * @param selector la stringa del selettore web
     * @throws IllegalArgumentException se il selettore non è valido
     */
    public WebSelector(String selector) {
        if (selector == null || selector.trim().isEmpty()) {
            throw new IllegalArgumentException("Il selettore non puo essere null o vuoto");
        }

        this.selector = selector.trim();
        validateXPath(this.selector);
        this.selectorType = SelectorType.XPATH;
    }

    /**
     * Factory method statico per creare un WebSelector
     * @param selector la stringa del selettore web
     * @return una nuova istanza di WebSelector
     * @throws IllegalArgumentException se il selettore non è valido
     */
    public static WebSelector of(String selector) {
        return new WebSelector(selector);
    }

    /**
     * Valida un XPath
     */
    private void validateXPath(String selector) {
        if (!isXPathNotation(selector)) {
            throw new IllegalArgumentException(
                String.format("Il selettore '%s' non e valido. Sono supportati solo XPath", selector)
            );
        }

        int openParens = selector.length() - selector.replace("(", "").length();
        int closeParens = selector.length() - selector.replace(")", "").length();
        if (openParens != closeParens) {
            throw new IllegalArgumentException("XPath non valido: parentesi non bilanciate");
        }

        int openBrackets = selector.length() - selector.replace("[", "").length();
        int closeBrackets = selector.length() - selector.replace("]", "").length();
        if (openBrackets != closeBrackets) {
            throw new IllegalArgumentException("XPath non valido: parentesi quadre non bilanciate");
        }
    }

    /**
     * Verifica se la notazione è un XPath valido
     */
    private boolean isXPathNotation(String selector) {
        return selector.startsWith("/")
                || selector.startsWith("./")
                || selector.startsWith(".//")
                || (selector.startsWith("(") && selector.contains("/"));
    }

    @Override
    public String toString() {
        return String.format("WebSelector{selector='%s', type=%s}", selector, selectorType.getDisplayName());
    }
}
