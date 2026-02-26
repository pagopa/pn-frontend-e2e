package it.pn.frontend.e2e.framework.core.capability.dispatcher;

import it.pn.frontend.e2e.framework.core.capability.handler.ICapabilityHandler;
import it.pn.frontend.e2e.framework.core.model.AbstractPresentationElement;
import it.pn.frontend.e2e.framework.core.model.Location;
import it.pn.frontend.e2e.framework.core.model.Selector;
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
class CapabilityDispatcherTest {

    private interface TestCapability {
        void action();
    }

    private static final class TestSelector implements Selector {
    }

    private static final class TestLocation implements Location {
    }

    private static final class TestElement extends AbstractPresentationElement<TestSelector, TestLocation> {
        private TestElement(TestSelector selector, TestLocation location) {
            super(selector, location);
        }
    }

    private static final class TestCapabilityDispatcher
            extends CapabilityDispatcher<TestSelector, TestLocation, TestElement> {
        private final TestSelector selector;
        private final TestLocation location;

        private TestCapabilityDispatcher(List<ICapabilityHandler<TestSelector, TestLocation, TestElement>> handlers,
                                         TestSelector selector,
                                         TestLocation location) {
            super(handlers);
            this.selector = selector;
            this.location = location;
        }

        @Override
        protected TestSelector getSelector(Method method) {
            return selector;
        }

        @Override
        protected TestLocation getLocation(Method method) {
            return location;
        }
    }

    @Mock
    private ICapabilityHandler<TestSelector, TestLocation, TestElement> firstHandler;

    @Mock
    private ICapabilityHandler<TestSelector, TestLocation, TestElement> secondHandler;

    private TestCapabilityDispatcher dispatcher;
    private Method actionMethod;
    private TestSelector selector;
    private TestLocation location;

    @BeforeEach
    void setUp() throws NoSuchMethodException {
        MockitoAnnotations.openMocks(this);
        List<ICapabilityHandler<TestSelector, TestLocation, TestElement>> handlers = new ArrayList<>();
        handlers.add(firstHandler);
        handlers.add(secondHandler);
        selector = new TestSelector();
        location = new TestLocation();
        dispatcher = new TestCapabilityDispatcher(handlers, selector, location);
        actionMethod = TestCapability.class.getMethod("action");
    }

    @Test
    @DisplayName("dovrebbe delegare al primo handler quando può gestire il method")
    void shouldDelegateToFirstHandlerWhenItCanHandle() {
        TestElement expectedElement = new TestElement(selector, location);
        when(firstHandler.canHandle(actionMethod)).thenReturn(true);
        when(firstHandler.handle(selector, location)).thenReturn(Optional.of(expectedElement));

        Optional<TestElement> result = dispatcher.dispatch(actionMethod);

        assertTrue(result.isPresent());
        assertSame(expectedElement, result.get());
        verify(firstHandler).canHandle(actionMethod);
        verify(firstHandler).handle(selector, location);
        verify(secondHandler, never()).handle(any(), any());
    }

    @Test
    @DisplayName("dovrebbe delegare al secondo handler quando il primo non può gestire")
    void shouldDelegateToSecondHandlerWhenFirstCannotHandle() {
        TestElement expectedElement = new TestElement(selector, location);
        when(firstHandler.canHandle(actionMethod)).thenReturn(false);
        when(secondHandler.canHandle(actionMethod)).thenReturn(true);
        when(secondHandler.handle(selector, location)).thenReturn(Optional.of(expectedElement));

        Optional<TestElement> result = dispatcher.dispatch(actionMethod);

        assertTrue(result.isPresent());
        assertSame(expectedElement, result.get());
        verify(firstHandler).canHandle(actionMethod);
        verify(firstHandler, never()).handle(any(), any());
        verify(secondHandler).canHandle(actionMethod);
        verify(secondHandler).handle(selector, location);
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
        assertTrue(exception.getMessage().contains(TestCapability.class.getSimpleName()));
        verify(firstHandler, never()).handle(any(), any());
        verify(secondHandler, never()).handle(any(), any());
    }

    @Test
    @DisplayName("dovrebbe gestire lista vuota di handler")
    void shouldHandleEmptyHandlerList() {
        dispatcher = new TestCapabilityDispatcher(new ArrayList<>(), selector, location);

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
        when(firstHandler.handle(selector, location)).thenReturn(Optional.empty());

        Optional<? extends Object> result = dispatcher.dispatch(actionMethod);

        assertTrue(result.isEmpty());
        verify(firstHandler).handle(selector, location);
    }

    @Test
    @DisplayName("dovrebbe propagare eccezioni dagli handler")
    void shouldPropagateExceptionsFromHandlers() {
        RuntimeException expectedException = new RuntimeException("handler error");
        when(firstHandler.canHandle(actionMethod)).thenReturn(true);
        when(firstHandler.handle(selector, location)).thenThrow(expectedException);

        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> dispatcher.dispatch(actionMethod)
        );

        assertEquals(expectedException, exception);
        verify(firstHandler).handle(selector, location);
    }
}
