package it.pn.frontend.e2e.presentation.dom.reader;

import org.openqa.selenium.WebElement;

import java.util.List;

public interface IDOMReader {
    WebElement readBySelector(String selector);
    List<WebElement> readAllBySelector(String selector);
}

