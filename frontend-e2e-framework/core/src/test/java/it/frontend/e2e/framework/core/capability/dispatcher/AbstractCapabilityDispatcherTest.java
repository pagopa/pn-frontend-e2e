package it.frontend.e2e.framework.core.capability.dispatcher;

import it.frontend.e2e.framework.core.capability.core.Gettable;
import it.frontend.e2e.framework.core.capability.dispatcher.impl.TestCapabilityDispatcher;
import it.frontend.e2e.framework.core.capability.dispatcher.handler.ICapabilityHandler;
import it.frontend.e2e.framework.core.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@DisplayName("CapabilityDispatcher")
class AbstractCapabilityDispatcherTest {

    private interface ActionCapability extends Gettable<TestSelector, TestLocation, TestElement> {
        void action();
    }

    @Mock
    private ICapabilityHandler firstHandler;

    @Mock
    private ICapabilityHandler secondHandler;

    private TestCapabilityDispatcher dispatcher;
    private Method actionMethod;
    private TestSelector selector;
    private TestLocation location;

    @BeforeEach
    void setUp() throws NoSuchMethodException {
        MockitoAnnotations.openMocks(this);
        List<ICapabilityHandler> handlers = new ArrayList<>();
        handlers.add(firstHandler);
        handlers.add(secondHandler);
        selector = new TestSelector();
        location = new TestLocation();
        dispatcher = new TestCapabilityDispatcher(handlers);
        actionMethod = ActionCapability.class.getMethod("action");
    }

    @Test
    @DisplayName("dovrebbe delegare al primo handler quando può gestire il method")
    void shouldDelegateToFirstHandlerWhenItCanHandle() {
        TestElement expectedElement = new TestElement(selector, location);
        when(firstHandler.canHandle(actionMethod)).thenReturn(true);
        when(firstHandler.handle(actionMethod)).thenReturn(Optional.of(expectedElement));

        Optional<TestElement> result = dispatcher.dispatch(actionMethod);

        assertTrue(result.isPresent());
        assertSame(expectedElement, result.get());
        verify(firstHandler).canHandle(actionMethod);
        verify(firstHandler).handle(actionMethod);
        verify(secondHandler, never()).handle(any());
    }

    @Test
    @DisplayName("dovrebbe delegare al secondo handler quando il primo non può gestire")
    void shouldDelegateToSecondHandlerWhenFirstCannotHandle() {
        TestElement expectedElement = new TestElement(selector, location);
        when(firstHandler.canHandle(actionMethod)).thenReturn(false);
        when(secondHandler.canHandle(actionMethod)).thenReturn(true);
        when(secondHandler.handle(actionMethod)).thenReturn(Optional.of(expectedElement));

        Optional<TestElement> result = dispatcher.dispatch(actionMethod);

        assertTrue(result.isPresent());
        assertSame(expectedElement, result.get());
        verify(firstHandler).canHandle(actionMethod);
        verify(firstHandler, never()).handle(any());
        verify(secondHandler).canHandle(actionMethod);
        verify(secondHandler).handle(actionMethod);
    }

    @Test
    @DisplayName("dovrebbe lanciare eccezione quando nessun handler può gestire il method")
    void shouldThrowExceptionWhenNoHandlerCanHandle() {
        when(firstHandler.canHandle(actionMethod)).thenReturn(false);
        when(secondHandler.canHandle(actionMethod)).thenReturn(false);

        IllegalStateException exception = assertThrows(
                IllegalStateException.class,
                () -> dispatcher.dispatch(actionMethod)
        );

        assertTrue(exception.getMessage().contains("No handler for"));
        assertTrue(exception.getMessage().contains(ActionCapability.class.getSimpleName()));
        verify(firstHandler, never()).handle(any());
        verify(secondHandler, never()).handle(any());
    }

    @Test
    @DisplayName("dovrebbe gestire lista vuota di handler")
    void shouldHandleEmptyHandlerList() {
        dispatcher = new TestCapabilityDispatcher(new ArrayList<>());

        IllegalStateException exception = assertThrows(
                IllegalStateException.class,
                () -> dispatcher.dispatch(actionMethod)
        );

        assertTrue(exception.getMessage().contains("No handler for"));
    }

    @Test
    @DisplayName("dovrebbe restituire Optional.empty se l'handler restituisce empty")
    void shouldReturnEmptyOptionalIfHandlerReturnsEmpty() {
        when(firstHandler.canHandle(actionMethod)).thenReturn(true);
        when(firstHandler.handle(actionMethod)).thenReturn(Optional.empty());

        Optional<? extends Object> result = dispatcher.dispatch(actionMethod);

        assertTrue(result.isEmpty());
        verify(firstHandler).handle(actionMethod);
    }

    @Test
    @DisplayName("dovrebbe propagare eccezioni dagli handler")
    void shouldPropagateExceptionsFromHandlers() {
        RuntimeException expectedException = new RuntimeException("handler error");
        when(firstHandler.canHandle(actionMethod)).thenReturn(true);
        when(firstHandler.handle(actionMethod)).thenThrow(expectedException);

        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> dispatcher.dispatch(actionMethod)
        );

        assertEquals(expectedException, exception);
        verify(firstHandler).handle(actionMethod);
    }
}
