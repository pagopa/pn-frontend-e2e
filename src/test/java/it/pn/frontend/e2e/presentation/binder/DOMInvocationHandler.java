package it.pn.frontend.e2e.presentation.binder;

import it.pn.frontend.e2e.presentation.IPresentationHandler;
import it.pn.frontend.e2e.presentation.annotation.DomAction;
import it.pn.frontend.e2e.presentation.annotation.DomField;
import it.pn.frontend.e2e.presentation.dom.parser.ParsedNode;
import it.pn.frontend.e2e.presentation.model.common.component.Component;
import it.pn.frontend.e2e.presentation.model.common.field.Field;
import it.pn.frontend.e2e.presentation.model.common.field.ReadableField;
import it.pn.frontend.e2e.presentation.model.common.field.WritableField;
import lombok.RequiredArgsConstructor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

@RequiredArgsConstructor
public class DOMInvocationHandler implements InvocationHandler {

    private final IPresentationHandler presentation;
    private final Class<?> boundType;

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        // @DomField
        if (method.isAnnotationPresent(DomField.class)) {

            String selector = method.getAnnotation(DomField.class).selector();
            Class<?> returnType = method.getReturnType();

            // Elemento elementare
            if (Field.class.isAssignableFrom(returnType)) {
                return createFieldProxy(selector, returnType);
            }

            // Componente/Pagina (Ricorsione)
            if (Component.class.isAssignableFrom(returnType)) {
                return createComponentProxy(selector, returnType);
            }

            throw new IllegalStateException(
                    "@DomField non supporta il tipo: " + returnType.getSimpleName()
            );
        }

        // @DomAction
        if (method.isAnnotationPresent(DomAction.class)) {
            presentation.click(method.getAnnotation(DomAction.class).value());
            return null;
        }

        // Metodi di Object
        if (method.getDeclaringClass() == Object.class) {
            return method.invoke(this, args);
        }

        throw new UnsupportedOperationException(
                "Metodo non gestito: " + method.getName()
        );
    }

    private Object createFieldProxy(String selector, Class<?> type) {

        InvocationHandler handler = (p, m, a) -> {

            String name = m.getName();

            if (name.equals("get") && ReadableField.class.isAssignableFrom(type)) {
                ParsedNode node = presentation.readBySelector(selector);
                return node.getText();
            }

            if (name.equals("set") && WritableField.class.isAssignableFrom(type)) {
                Object value = a[0];
                presentation.clear(selector);
                presentation.type(selector, value.toString());
                return null;
            }

            throw new UnsupportedOperationException(
                    "Metodo " + name + " non permesso per tipo " + type.getSimpleName()
            );
        };

        return Proxy.newProxyInstance(
                type.getClassLoader(),
                new Class<?>[]{type},
                handler
        );
    }

    private Object createComponentProxy(String selector, Class<?> componentType) {

        IPresentationHandler scoped = presentation.withRoot(selector);

        return Proxy.newProxyInstance(
                componentType.getClassLoader(),
                new Class<?>[]{componentType},
                new DOMInvocationHandler(scoped, componentType) // o passare scoped come unico
        );
    }
}
