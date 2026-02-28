package it.frontend.e2e.framework.web.model;

import it.frontend.e2e.framework.core.model.Selector;
import lombok.Getter;

@Getter
public class WebSelector implements Selector {

    /**
     * Enum per rappresentare i tipi di selettore web supportati
     */
    public enum SelectorType {
        XPATH("XPath"),
        ID("ID"),
        CSS_QUERY("CSS Query"),
        CSS_SELECTOR("CSS Selector");

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
            throw new IllegalArgumentException("Il selettore non può essere null o vuoto");
        }

        this.selector = selector.trim();
        this.selectorType = detectSelectorType(this.selector);
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
     * Rileva il tipo di selettore in base al formato della stringa
     * @param selector la stringa del selettore
     * @return il tipo di selettore rilevato
     * @throws IllegalArgumentException se il selettore non è riconosciuto
     */
    private SelectorType detectSelectorType(String selector) {
        // Verifica XPath: inizia con // o è (//...
        if (selector.startsWith("//")) {
            validateXPath(selector);
            return SelectorType.XPATH;
        }

        if (selector.startsWith("(") && isValidXPathWithParenthesis(selector)) {
            validateXPath(selector);
            return SelectorType.XPATH;
        }

        // Verifica CSS Selector: contiene selettori complessi con combinatori
        if (isComplexCssSelector(selector)) {
            validateCssSelector(selector);
            return SelectorType.CSS_SELECTOR;
        }

        // Verifica CSS Query: selettore CSS semplice (con punti, hash, attributi, o tag)
        if (isValidCssQuery(selector)) {
            validateCssQuery(selector);
            return SelectorType.CSS_QUERY;
        }

        // Verifica ID: non contiene spazi, punti, hash o simboli CSS comuni
        if (isValidId(selector)) {
            return SelectorType.ID;
        }

        throw new IllegalArgumentException(
            String.format("Il selettore '%s' non è valido. Deve essere un XPath, ID, CSS Query o CSS Selector", selector)
        );
    }

    /**
     * Verifica se una stringa che inizia con ( è un XPath valido
     */
    private boolean isValidXPathWithParenthesis(String selector) {
        // Un XPath che inizia con ( deve contenere // dentro le parentesi
        // es: (//div[@id='root']) è valido
        // es: (@#$%^&*()) non è valido
        return selector.contains("//");
    }

    /**
     * Valida e identifica se è un ID valido
     */
    private boolean isValidId(String selector) {
        // Un ID è una stringa semplice senza spazi e senza caratteri speciali CSS/XPath
        if (selector.contains("/") || selector.contains("[") || selector.contains("(") ||
            selector.contains(">") || selector.contains("+") || selector.contains("~") ||
            selector.contains(".") || selector.contains("#")) {
            return false;
        }

        // ID HTML validi: alfanumerici, trattini, underscore
        // Escludiamo i tag HTML standard (div, span, p, a, button, etc.)
        if (!isHtmlTag(selector)) {
            return selector.matches("^[a-zA-Z0-9_-]+$");
        }
        return false;
    }

    /**
     * Verifica se il selettore è un tag HTML standard
     */
    private boolean isHtmlTag(String selector) {
        // Tag HTML comuni
        String[] htmlTags = {
            "div", "span", "p", "a", "button", "form", "input", "select", "textarea",
            "table", "tr", "td", "th", "thead", "tbody", "tfoot", "h1", "h2", "h3",
            "h4", "h5", "h6", "ul", "ol", "li", "label", "img", "br", "hr", "section",
            "article", "header", "footer", "nav", "aside", "main", "figure", "figcaption"
        };
        for (String tag : htmlTags) {
            if (selector.equals(tag)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Identifica se è un CSS Selector complesso (con combinatori)
     */
    private boolean isComplexCssSelector(String selector) {
        // Contiene combinatori CSS (>, +, ~) o pattern complessi
        return selector.contains(">") || selector.contains("+") || selector.contains("~");
    }

    /**
     * Valida un CSS Selector
     */
    private void validateCssSelector(String selector) {
        // Validazione base: parentesi bilanciate, non XPath
        int openBrackets = selector.length() - selector.replace("[", "").length();
        int closeBrackets = selector.length() - selector.replace("]", "").length();
        if (openBrackets != closeBrackets) {
            throw new IllegalArgumentException("CSS Selector non valido: parentesi quadre non bilanciate");
        }
    }

    /**
     * Valida e identifica un CSS Query
     */
    private boolean isValidCssQuery(String selector) {
        // Esclude XPath e selettori con combinatori CSS
        if (selector.contains("/") || selector.contains(">") || selector.contains("+") || selector.contains("~")) {
            return false;
        }

        // Contiene punti (classi), hash (ID), attributi CSS, o tag HTML semplici
        if (selector.contains(".") || selector.contains("#") || selector.contains("[") || isHtmlTag(selector)) {
            return true;
        }

        // Tag HTML con classe o attributi
        if (selector.matches("^[a-zA-Z][a-zA-Z0-9_-]*\\[.*\\]$") ||
            selector.matches("^[a-zA-Z][a-zA-Z0-9_-]*\\..*$")) {
            return true;
        }

        return false;
    }

    /**
     * Valida una CSS Query
     */
    private void validateCssQuery(String selector) {
        // Validazione parentesi quadre bilanciate
        int openBrackets = selector.length() - selector.replace("[", "").length();
        int closeBrackets = selector.length() - selector.replace("]", "").length();
        if (openBrackets != closeBrackets) {
            throw new IllegalArgumentException("CSS Query non valida: parentesi quadre non bilanciate");
        }

        // Se contiene #, deve essere un selettore CSS valido (#id o #id.class, ecc.)
        if (selector.contains("#")) {
            if (!isValidCssIdSelector(selector)) {
                throw new IllegalArgumentException("CSS Query non valida: selettore ID non valido");
            }
        }

        // Se contiene ., deve essere un selettore CSS valido (.class o tag.class, ecc.)
        if (selector.contains(".")) {
            if (!isValidCssClassSelector(selector)) {
                throw new IllegalArgumentException("CSS Query non valida: selettore classe non valido");
            }
        }
    }

    /**
     * Verifica se è un selettore CSS ID valido
     */
    private boolean isValidCssIdSelector(String selector) {
        // Formato: #id oppure tag#id oppure #id.class oppure tag#id.class
        // Caratteri validi dopo #: alfanumerici, trattini, underscore
        return selector.matches("^([a-zA-Z][a-zA-Z0-9_-]*)?#[a-zA-Z0-9_-]+(\\.?[a-zA-Z0-9_-]*)*\\[?.*\\]?$");
    }

    /**
     * Verifica se è un selettore CSS classe valido
     */
    private boolean isValidCssClassSelector(String selector) {
        // Formato: .class oppure tag.class oppure .class.class2 oppure tag.class.class2
        return selector.matches("^([a-zA-Z][a-zA-Z0-9_-]*)?(\\.?[a-zA-Z0-9_-]+)+\\[?.*\\]?$");
    }

    /**
     * Valida un XPath
     */
    private void validateXPath(String selector) {
        // Validazione base per XPath: parentesi bilanciate
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

    @Override
    public String toString() {
        return String.format("WebSelector{selector='%s', type=%s}", selector, selectorType.getDisplayName());
    }
}

