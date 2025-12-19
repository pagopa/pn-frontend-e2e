package it.pn.frontend.e2e.presentation.dom.reader;

import it.pn.frontend.e2e.presentation.dom.parser.ParsedNode;

import java.util.List;

public interface IDOMReader {
    ParsedNode readBySelector(String selector);
    List<ParsedNode> readAllBySelector(String selector);
    IDOMReader withRoot(String rootSelector);
}

