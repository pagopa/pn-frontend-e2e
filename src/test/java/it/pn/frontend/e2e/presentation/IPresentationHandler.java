package it.pn.frontend.e2e.presentation;

import it.pn.frontend.e2e.presentation.dom.interaction.IDOMInteraction;
import it.pn.frontend.e2e.presentation.dom.parser.IDOMParser;
import it.pn.frontend.e2e.presentation.dom.reader.IDOMReader;

public interface IPresentationHandler extends IDOMReader, IDOMParser, IDOMInteraction {
    <T> T parse(Class<T> pageInterface);
}
