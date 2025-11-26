package it.pn.frontend.e2e.presentation.dom.parser;

import org.openqa.selenium.WebElement;

public interface IDOMParser {
    ParsedNode parse(WebElement element);

}

