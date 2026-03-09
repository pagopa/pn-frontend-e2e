package it.frontend.e2e.framework.web.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("WebSelector")
class WebSelectorTest {

    @Test
    @DisplayName("accetta un XPath assoluto")
    void shouldAcceptAbsoluteXPath() {
        WebSelector selector = new WebSelector("//div[@id='root']");

        assertEquals("//div[@id='root']", selector.getSelector());
        assertEquals(WebSelector.SelectorType.XPATH, selector.getSelectorType());
    }

    @Test
    @DisplayName("accetta un XPath relativo")
    void shouldAcceptRelativeXPath() {
        WebSelector selector = new WebSelector(".//button[@type='submit']");

        assertEquals(".//button[@type='submit']", selector.getSelector());
        assertEquals(WebSelector.SelectorType.XPATH, selector.getSelectorType());
    }

    @Test
    @DisplayName("trim del selettore")
    void shouldTrimSelector() {
        WebSelector selector = new WebSelector("  /div[1]/span  ");

        assertEquals("/div[1]/span", selector.getSelector());
        assertEquals(WebSelector.SelectorType.XPATH, selector.getSelectorType());
    }

    @Test
    @DisplayName("rifiuta selettori non XPath")
    void shouldRejectNonXPathSelectors() {
        assertThrows(IllegalArgumentException.class, () -> new WebSelector("myElement"));
        assertThrows(IllegalArgumentException.class, () -> new WebSelector("#root > div"));
        assertThrows(IllegalArgumentException.class, () -> new WebSelector("div.container"));
    }

    @Test
    @DisplayName("rifiuta parentesi non bilanciate")
    void shouldRejectUnbalancedParentheses() {
        assertThrows(IllegalArgumentException.class, () -> new WebSelector("(//div[@id='root']"));
    }

    @Test
    @DisplayName("rifiuta parentesi quadre non bilanciate")
    void shouldRejectUnbalancedBrackets() {
        assertThrows(IllegalArgumentException.class, () -> new WebSelector("//div[@id='root'"));
    }

    @Test
    @DisplayName("rifiuta null o stringhe vuote")
    void shouldRejectNullOrBlankSelector() {
        assertThrows(IllegalArgumentException.class, () -> new WebSelector(null));
        assertThrows(IllegalArgumentException.class, () -> new WebSelector(""));
        assertThrows(IllegalArgumentException.class, () -> new WebSelector("   "));
    }

    @Test
    @DisplayName("toString include valore e tipo")
    void shouldRenderToString() {
        WebSelector selector = new WebSelector("//div[@id='root']");
        String text = selector.toString();

        assertTrue(text.contains("//div[@id='root']"));
        assertTrue(text.contains("XPath"));
    }
}
