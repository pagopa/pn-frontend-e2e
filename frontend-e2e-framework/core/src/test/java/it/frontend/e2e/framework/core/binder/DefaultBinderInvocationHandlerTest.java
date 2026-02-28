package it.frontend.e2e.framework.core.binder;

import it.frontend.e2e.framework.core.binder.impl.TestInvocationHandlerDefault;
import it.frontend.e2e.framework.core.capability.dispatcher.ICapabilityDispatcher;
import it.frontend.e2e.framework.core.model.TestElement;
import it.frontend.e2e.framework.core.model.TestLocation;
import it.frontend.e2e.framework.core.model.TestSelector;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@DisplayName("AbstractPresentationInvocationHandler")
class DefaultBinderInvocationHandlerTest {

    public interface TestInterface {
        default String defaultMethod() {
            return "default";
        }

        Optional<TestElement> element();

        Optional<TestElement> elementWithArg(String value);
    }

    @Mock
    private ICapabilityDispatcher dispatcher;

    private DefaultBinderInvocationHandler handler;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        handler = new TestInvocationHandlerDefault(dispatcher);
    }

    @Test
    @DisplayName("dovrebbe delegare metodi default all'implementazione dell'interfaccia")
    void shouldDelegateDefaultMethodsToInterface() throws Throwable {
        TestInterface proxy = createProxyInstance();
        Method defaultMethod = TestInterface.class.getMethod("defaultMethod");

        Object result = handler.invoke(proxy, defaultMethod, new Object[]{});

        assertEquals("default", result);
        verify(dispatcher, never()).dispatch(any(), any(), eq(""));
    }

    @Test
    @DisplayName("dovrebbe delegare metodi di Object a se stesso")
    void shouldDelegateObjectMethodsToItself() throws Throwable {
        TestInterface proxy = createProxyInstance();
        Method toStringMethod = Object.class.getMethod("toString");

        Object result = handler.invoke(proxy, toStringMethod, new Object[]{});

        assertNotNull(result);
        verify(dispatcher, never()).dispatch(any(), any(), eq(""));
    }

  @Test
  @DisplayName("dovrebbe delegare al dispatcher l'implementazione dei metodi custom")
  void shouldDispatchCustomMethods() throws Throwable {
      TestInterface proxy = createProxyInstance();
      Method method = TestInterface.class.getMethod("element");
      TestElement expectedElement = new TestElement(new TestSelector(), new TestLocation());
      Optional<TestElement> expectedResult = Optional.of(expectedElement);
      Object[] args = null;

      when(dispatcher.dispatch(method, args,"")).thenReturn(expectedResult);

      Object result = handler.invoke(proxy, method, args);

      assertEquals(expectedResult, result);
      verify(dispatcher, times(1)).dispatch(method, args,"");
  }

    @Test
    @DisplayName("dovrebbe gestire metodi con argomenti")
    void shouldHandleMethodsWithArguments() throws Throwable {
        TestInterface proxy = createProxyInstance();
        Method method = TestInterface.class.getMethod("elementWithArg", String.class);
        Optional<TestElement> expectedResult = Optional.empty();

        when(dispatcher.dispatch(eq(method), any(), eq(""))).thenReturn(expectedResult);

        Object result = handler.invoke(proxy, method, new Object[]{"value"});

        assertEquals(expectedResult, result);
        verify(dispatcher, times(1)).dispatch(eq(method), any(), eq(""));
    }

        @Test
        @DisplayName("dovrebbe propagare eccezioni dal dispatcher")
        void shouldPropagateDispatcherExceptions() throws Throwable {
            TestInterface proxy = createProxyInstance();
            Method method = TestInterface.class.getMethod("element");
            RuntimeException exception = new RuntimeException("dispatcher error");

            when(dispatcher.dispatch(method, null, "")).thenThrow(exception);

            assertThrows(RuntimeException.class, () ->
                    handler.invoke(proxy, method, null)
            );
        }

    private TestInterface createProxyInstance() {
        return (TestInterface) Proxy.newProxyInstance(
                TestInterface.class.getClassLoader(),
                new Class[]{TestInterface.class},
                handler
        );
    }
}
