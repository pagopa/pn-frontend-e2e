package it.pn.frontend.e2e.presentation.dom.reader;

import it.pn.frontend.e2e.presentation.dom.parser.ISeleniumDOMParser;
import it.pn.frontend.e2e.presentation.dom.parser.ParsedNode;
import lombok.RequiredArgsConstructor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.NoSuchElementException;

public class SeleniumDOMReader implements IDOMReader {

    private final WebDriver driver;
    private final ISeleniumDOMParser parser;
    private final String rootSelector;

    public SeleniumDOMReader(WebDriver driver, ISeleniumDOMParser parser) {
        this(driver, parser, "");
    }

    private SeleniumDOMReader(WebDriver driver, ISeleniumDOMParser parser, String rootSelector) {
        this.driver = driver;
        this.parser = parser;
        this.rootSelector = rootSelector;
    }

    @Override
    public ParsedNode readBySelector(String selector) {
        try {
            String scopedSelector = scope(selector);

            // per ora assumiamo CSS selector
            WebElement element = driver.findElement(By.cssSelector(scopedSelector));
            return parser.parse(element);

        } catch (NoSuchElementException e) {
            return null;
        }
    }

    @Override
    public List<ParsedNode> readAllBySelector(String selector) {
        String scopedSelector = scope(selector);

        return driver.findElements(By.cssSelector(scopedSelector))
                .stream()
                .map(parser::parse)
                .toList();
    }

    @Override
    public IDOMReader withRoot(String rootSelector) {

        String newRoot = this.rootSelector.isEmpty()
                ? rootSelector
                : this.rootSelector + " " + rootSelector;

        return new SeleniumDOMReader(driver, parser, newRoot);
    }

    private String scope(String selector) {
        if (rootSelector == null || rootSelector.isEmpty()) {
            return selector;
        }
        return rootSelector + " " + selector;
    }
}


