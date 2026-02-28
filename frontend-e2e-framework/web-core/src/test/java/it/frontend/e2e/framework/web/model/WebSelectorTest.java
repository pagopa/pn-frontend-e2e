package it.frontend.e2e.framework.web.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("WebSelector")
class WebSelectorTest {

    @Test
    @DisplayName("dovrebbe riconoscere correttamente un XPath che inizia con //")
    void shouldRecognizeXPathStartingWithSlashes() {
        WebSelector selector = new WebSelector("//div[@id='root']");

        assertEquals("//div[@id='root']", selector.getSelector());
        assertEquals(WebSelector.SelectorType.XPATH, selector.getSelectorType());
    }

    @Test
    @DisplayName("dovrebbe riconoscere correttamente un XPath che inizia con parentesi")
    void shouldRecognizeXPathStartingWithParenthesis() {
        WebSelector selector = new WebSelector("(//div[@id='root'])");

        assertEquals("(//div[@id='root'])", selector.getSelector());
        assertEquals(WebSelector.SelectorType.XPATH, selector.getSelectorType());
    }

    @Test
    @DisplayName("dovrebbe riconoscere correttamente un ID semplice")
    void shouldRecognizeSimpleId() {
        WebSelector selector = new WebSelector("myElement");

        assertEquals("myElement", selector.getSelector());
        assertEquals(WebSelector.SelectorType.ID, selector.getSelectorType());
    }

    @Test
    @DisplayName("dovrebbe riconoscere correttamente un ID con trattini e underscore")
    void shouldRecognizeIdWithDashesAndUnderscore() {
        WebSelector selector = new WebSelector("my-element_id");

        assertEquals("my-element_id", selector.getSelector());
        assertEquals(WebSelector.SelectorType.ID, selector.getSelectorType());
    }

    @Test
    @DisplayName("dovrebbe riconoscere correttamente un ID numerico")
    void shouldRecognizeNumericId() {
        WebSelector selector = new WebSelector("element123");

        assertEquals("element123", selector.getSelector());
        assertEquals(WebSelector.SelectorType.ID, selector.getSelectorType());
    }

    @Test
    @DisplayName("dovrebbe riconoscere correttamente un CSS Query con classe singola")
    void shouldRecognizeSingleClassCssQuery() {
        WebSelector selector = new WebSelector(".myClass");

        assertEquals(".myClass", selector.getSelector());
        assertEquals(WebSelector.SelectorType.CSS_QUERY, selector.getSelectorType());
    }

    @Test
    @DisplayName("dovrebbe riconoscere correttamente un CSS Query con ID")
    void shouldRecognizeIdCssQuery() {
        WebSelector selector = new WebSelector("#root");

        assertEquals("#root", selector.getSelector());
        assertEquals(WebSelector.SelectorType.CSS_QUERY, selector.getSelectorType());
    }

    @Test
    @DisplayName("dovrebbe riconoscere correttamente un CSS Query con tag HTML")
    void shouldRecognizeTagNameCssQuery() {
        WebSelector selector = new WebSelector("div");

        assertEquals("div", selector.getSelector());
        assertEquals(WebSelector.SelectorType.CSS_QUERY, selector.getSelectorType());
    }

    @Test
    @DisplayName("dovrebbe riconoscere correttamente un CSS Selector complesso con combinatore >")
    void shouldRecognizeComplexCssSelectorWithChildCombinator() {
        WebSelector selector = new WebSelector("#root > div > div.MuiGrid-root");

        assertEquals("#root > div > div.MuiGrid-root", selector.getSelector());
        assertEquals(WebSelector.SelectorType.CSS_SELECTOR, selector.getSelectorType());
    }

    @Test
    @DisplayName("dovrebbe riconoscere correttamente un CSS Selector con combinatore +")
    void shouldRecognizeComplexCssSelectorWithAdjacentCombinator() {
        WebSelector selector = new WebSelector("div + p");

        assertEquals("div + p", selector.getSelector());
        assertEquals(WebSelector.SelectorType.CSS_SELECTOR, selector.getSelectorType());
    }

    @Test
    @DisplayName("dovrebbe riconoscere correttamente un CSS Selector con combinatore ~")
    void shouldRecognizeComplexCssSelectorWithGeneralSiblingCombinator() {
        WebSelector selector = new WebSelector("div ~ p");

        assertEquals("div ~ p", selector.getSelector());
        assertEquals(WebSelector.SelectorType.CSS_SELECTOR, selector.getSelectorType());
    }

    @Test
    @DisplayName("dovrebbe riconoscere correttamente un CSS Selector MUI complesso")
    void shouldRecognizeComplexMuiCssSelector() {
        WebSelector selector = new WebSelector("#root > div > div.MuiGrid-root.MuiGrid-container.MuiGrid-direction-xs-column.css-pmjnd8");

        assertEquals("#root > div > div.MuiGrid-root.MuiGrid-container.MuiGrid-direction-xs-column.css-pmjnd8", selector.getSelector());
        assertEquals(WebSelector.SelectorType.CSS_SELECTOR, selector.getSelectorType());
    }

    @Test
    @DisplayName("dovrebbe trimmare gli spazi vuoti dal selettore")
    void shouldTrimWhitespace() {
        WebSelector selector = new WebSelector("  myElement  ");

        assertEquals("myElement", selector.getSelector());
        assertEquals(WebSelector.SelectorType.ID, selector.getSelectorType());
    }

    @Test
    @DisplayName("dovrebbe lanciare eccezione quando il selettore è null")
    void shouldThrowExceptionWhenSelectorIsNull() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new WebSelector(null)
        );
    }

    @Test
    @DisplayName("dovrebbe lanciare eccezione quando il selettore è vuoto")
    void shouldThrowExceptionWhenSelectorIsEmpty() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new WebSelector("")
        );
    }

    @Test
    @DisplayName("dovrebbe lanciare eccezione quando il selettore è solo spazi")
    void shouldThrowExceptionWhenSelectorIsOnlyWhitespace() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new WebSelector("   ")
        );
    }

    @Test
    @DisplayName("dovrebbe lanciare eccezione quando il selettore non è valido")
    void shouldThrowExceptionWhenSelectorIsInvalid() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new WebSelector("@#$%^&*()")
        );
    }

    @Test
    @DisplayName("dovrebbe lanciare eccezione quando XPath ha parentesi non bilanciate")
    void shouldThrowExceptionWhenXPathHasUnbalancedParentheses() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new WebSelector("//div[@id='root'")
        );
    }

    @Test
    @DisplayName("dovrebbe lanciare eccezione quando CSS Selector ha parentesi quadre non bilanciate")
    void shouldThrowExceptionWhenCssSelectorHasUnbalancedBrackets() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new WebSelector("div[class='test'")
        );
    }

    @Test
    @DisplayName("dovrebbe restituire una stringa di rappresentazione significativa")
    void shouldReturnMeaningfulToString() {
        WebSelector selector = new WebSelector("//div[@id='root']");
        String toString = selector.toString();

        assertTrue(toString.contains("//div[@id='root']"));
        assertTrue(toString.contains("XPath"));
    }

    @Test
    @DisplayName("dovrebbe preservare il tipo di selettore per due istanze diverse dello stesso selettore")
    void shouldPreserveSelectorTypeForDifferentInstances() {
        WebSelector selector1 = new WebSelector("#myId");
        WebSelector selector2 = new WebSelector("#myId");

        assertEquals(selector1.getSelectorType(), selector2.getSelectorType());
        assertEquals(WebSelector.SelectorType.CSS_QUERY, selector1.getSelectorType());
    }

    @Test
    @DisplayName("dovrebbe gestire XPath con attributi multipli")
    void shouldHandleXPathWithMultipleAttributes() {
        WebSelector selector = new WebSelector("//div[@id='root'][@class='container']");

        assertEquals("//div[@id='root'][@class='container']", selector.getSelector());
        assertEquals(WebSelector.SelectorType.XPATH, selector.getSelectorType());
    }

    @Test
    @DisplayName("dovrebbe gestire XPath con funzioni XPath")
    void shouldHandleXPathWithFunctions() {
        WebSelector selector = new WebSelector("//div[contains(@id, 'root')]");

        assertEquals("//div[contains(@id, 'root')]", selector.getSelector());
        assertEquals(WebSelector.SelectorType.XPATH, selector.getSelectorType());
    }

    @Test
    @DisplayName("dovrebbe gestire CSS Query con tag e classe combinati")
    void shouldHandleCssQueryWithTagAndClass() {
        WebSelector selector = new WebSelector("div.container");

        assertEquals("div.container", selector.getSelector());
        assertEquals(WebSelector.SelectorType.CSS_QUERY, selector.getSelectorType());
    }

    @Test
    @DisplayName("dovrebbe gestire CSS Selector con attributi")
    void shouldHandleCssSelectorWithAttributes() {
        WebSelector selector = new WebSelector("div[data-test='value']");

        assertEquals("div[data-test='value']", selector.getSelector());
        assertEquals(WebSelector.SelectorType.CSS_QUERY, selector.getSelectorType());
    }
}
