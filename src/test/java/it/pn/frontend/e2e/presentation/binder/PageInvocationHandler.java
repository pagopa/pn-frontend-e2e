package it.pn.frontend.e2e.presentation.binder;

import it.pn.frontend.e2e.presentation.aop.DomAction;
import it.pn.frontend.e2e.presentation.aop.DomField;
import it.pn.frontend.e2e.presentation.dom.interaction.IDOMInteraction;
import it.pn.frontend.e2e.presentation.dom.parser.IDOMParser;
import it.pn.frontend.e2e.presentation.dom.parser.ParsedNode;
import it.pn.frontend.e2e.presentation.dom.reader.IDOMReader;
import org.openqa.selenium.WebElement;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class PageInvocationHandler implements InvocationHandler {

    private final IDOMReader reader;
    private final IDOMParser parser;
    private final IDOMInteraction interaction;
    private final Class<?> pageInterface;

    public PageInvocationHandler(IDOMReader reader,
                                 IDOMParser parser,
                                 IDOMInteraction interaction,
                                 Class<?> pageInterface) {

        this.reader = reader;
        this.parser = parser;
        this.interaction = interaction;
        this.pageInterface = pageInterface;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        // Caso 1 → Metodo annotato con @DomField (binding lettura/scrittura)
        if (method.isAnnotationPresent(DomField.class)) {
            String selector = method.getAnnotation(DomField.class).value();

            // GETTER
            if (method.getParameterCount() == 0) {
                WebElement el = reader.readBySelector(selector);
                ParsedNode node = parser.parse(el);
                return node.getText();
            }

            // SETTER
            if (method.getParameterCount() == 1) {
                Object value = args[0];
                interaction.clear(selector);
                interaction.type(selector, value.toString());
                return null;
            }
        }

        // Caso 2 → Metodo annotato con @DomAction (azioni DOM)
        if (method.isAnnotationPresent(DomAction.class)) {
            String selector = method.getAnnotation(DomAction.class).value();
            interaction.click(selector);
            return null;
        }

        // Caso 3 → equals(), hashCode(), toString()
        if (method.getDeclaringClass() == Object.class) {
            return method.invoke(this, args);
        }

        throw new UnsupportedOperationException(
                "Metodo non gestito: " + method.getName()
        );
    }
}
