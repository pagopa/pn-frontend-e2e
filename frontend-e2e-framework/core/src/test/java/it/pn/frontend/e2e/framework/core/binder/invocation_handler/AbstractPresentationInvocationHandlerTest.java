package it.pn.frontend.e2e.framework.core.binder.invocation_handler;

import it.pn.frontend.e2e.framework.core.binder.invocation_handler.context.BaseInvocationContext;
import it.pn.frontend.e2e.framework.core.capability.dispatcher.ICapabilityDispatcher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@DisplayName("AbstractPresentationInvocationHandler")
class AbstractPresentationInvocationHandlerTest {

    @Mock
    private ICapabilityDispatcher<BaseInvocationContext> dispatcher;

    private AbstractPresentationInvocationHandler<BaseInvocationContext> handler;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        handler = new ConcreteInvocationHandler(dispatcher);
    }

    @Test
    @DisplayName("dovrebbe delegare metodi default all'implementazione dell'interfaccia")
    void shouldDelegateDefaultMethodsToInterface() throws Throwable {
        TestInterface proxy = createProxyInstance();
        Method defaultMethod = TestInterface.class.getMethod("defaultMethod");

        Object result = handler.invoke(proxy, defaultMethod, new Object[]{});

        assertEquals("default", result);
        verify(dispatcher, never()).dispatch(any());
    }

    @Test
    @DisplayName("dovrebbe delegare metodi di Object a se stesso")
    void shouldDelegateObjectMethodsToItself() throws Throwable {
        Object proxy = new Object();
        Method toStringMethod = Object.class.getMethod("toString");

        Object result = handler.invoke(proxy, toStringMethod, new Object[]{});

        assertNotNull(result);
        verify(dispatcher, never()).dispatch(any());
    }

    @Test
    @DisplayName("dovrebbe creare il context e delegare al dispatcher l'implementazione dei metodi custom")
    void shouldCreateContextAndDispatchForCustomMethods() throws Throwable {
        TestInterface proxy = createProxyInstance();
        Method customMethod = TestInterface.class.getMethod("customMethod", String.class);
        Object[] args = {"test"};
        Object expectedResult = "dispatched";

        when(dispatcher.dispatch(any())).thenReturn(expectedResult);

        Object result = handler.invoke(proxy, customMethod, args);

        assertEquals(expectedResult, result);
        verify(dispatcher, times(1)).dispatch(any());
    }

    @Test
    @DisplayName("dovrebbe propagare eccezioni dal dispatcher")
    void shouldPropagateDispatcherExceptions() throws Throwable {
        TestInterface proxy = createProxyInstance();
        Method customMethod = TestInterface.class.getMethod("customMethod", String.class);
        RuntimeException exception = new RuntimeException("dispatcher error");

        when(dispatcher.dispatch(any())).thenThrow(exception);

        assertThrows(RuntimeException.class, () ->
                handler.invoke(proxy, customMethod, new Object[]{"test"})
        );
    }

    @Test
    @DisplayName("dovrebbe gestire metodi con argomenti multipli")
    void shouldHandleMethodsWithMultipleArguments() throws Throwable {
        TestInterface proxy = createProxyInstance();
        Method method = TestInterface.class.getMethod("multiArgMethod", String.class, int.class);
        Object[] args = {"arg1", 42};

        when(dispatcher.dispatch(any())).thenReturn("result");

        Object result = handler.invoke(proxy, method, args);

        assertNotNull(result);
        verify(dispatcher, times(1)).dispatch(any());
    }

    @Test
    @DisplayName("dovrebbe gestire metodi senza argomenti")
    void shouldHandleMethodsWithoutArguments() throws Throwable {
        TestInterface proxy = createProxyInstance();
        Method method = TestInterface.class.getMethod("noArgMethod");

        when(dispatcher.dispatch(any())).thenReturn("result");

        Object result = handler.invoke(proxy, method, new Object[]{});

        assertNotNull(result);
        verify(dispatcher, times(1)).dispatch(any());
    }

    private TestInterface createProxyInstance() {
        return (TestInterface) java.lang.reflect.Proxy.newProxyInstance(
                TestInterface.class.getClassLoader(),
                new Class[]{TestInterface.class},
                handler
        );
    }

    private static class ConcreteInvocationHandler extends AbstractPresentationInvocationHandler<BaseInvocationContext> {
        ConcreteInvocationHandler(ICapabilityDispatcher<BaseInvocationContext> dispatcher) {
            super(dispatcher);
        }

        @Override
        protected BaseInvocationContext createContext(Object proxy, Method method, Object[] args) {
            Method mockMethod = mock(Method.class);
            return new BaseInvocationContext(mockMethod);
        }
    }

    interface TestInterface {
        default String defaultMethod() {
            return "default";
        }

        void customMethod(String arg);

        void multiArgMethod(String arg1, int arg2);

        void noArgMethod();
    }
}

