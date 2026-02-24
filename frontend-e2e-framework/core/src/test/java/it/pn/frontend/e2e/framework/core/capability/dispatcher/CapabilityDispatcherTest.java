package it.pn.frontend.e2e.framework.core.capability.dispatcher;

import it.pn.frontend.e2e.framework.core.binder.invocation_handler.context.BaseInvocationContext;
import it.pn.frontend.e2e.framework.core.capability.handler.ICapabilityHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@DisplayName("CapabilityDispatcher")
class CapabilityDispatcherTest {

    @Mock
    private ICapabilityHandler<BaseInvocationContext> firstHandler;

    @Mock
    private ICapabilityHandler<BaseInvocationContext> secondHandler;

    @Mock
    private BaseInvocationContext context;

    private CapabilityDispatcher<BaseInvocationContext> dispatcher;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        List<ICapabilityHandler<BaseInvocationContext>> handlers = new ArrayList<>();
        handlers.add(firstHandler);
        handlers.add(secondHandler);
        dispatcher = new CapabilityDispatcher<>(handlers);
    }

    @Test
    @DisplayName("dovrebbe delegare al primo handler quando può gestire il context")
    void shouldDelegateToFirstHandlerWhenItCanHandle() {
        Object expectedResult = "result";
        when(firstHandler.canHandle(context)).thenReturn(true);
        when(firstHandler.handle(context)).thenReturn(expectedResult);

        Object result = dispatcher.dispatch(context);

        assertEquals(expectedResult, result);
        verify(firstHandler).canHandle(context);
        verify(firstHandler).handle(context);
        verify(secondHandler, never()).handle(any());
    }

    @Test
    @DisplayName("dovrebbe delegare al secondo handler quando il primo non può gestire")
    void shouldDelegateToSecondHandlerWhenFirstCannotHandle() {
        Object expectedResult = "result";
        when(firstHandler.canHandle(context)).thenReturn(false);
        when(secondHandler.canHandle(context)).thenReturn(true);
        when(secondHandler.handle(context)).thenReturn(expectedResult);

        Object result = dispatcher.dispatch(context);

        assertEquals(expectedResult, result);
        verify(firstHandler).canHandle(context);
        verify(firstHandler, never()).handle(any());
        verify(secondHandler).canHandle(context);
        verify(secondHandler).handle(context);
    }

    @Test
    @DisplayName("dovrebbe lanciare eccezione quando nessun handler può gestire il context")
    void shouldThrowExceptionWhenNoHandlerCanHandle() {
        when(firstHandler.canHandle(context)).thenReturn(false);
        when(secondHandler.canHandle(context)).thenReturn(false);

        IllegalStateException exception = assertThrows(
                IllegalStateException.class,
                () -> dispatcher.dispatch(context)
        );

        assertTrue(exception.getMessage().contains("No handler for"));
        verify(firstHandler, never()).handle(any());
        verify(secondHandler, never()).handle(any());
    }

    @Test
    @DisplayName("dovrebbe sostituire gli handler esistenti con nuovi handler")
    void shouldReplaceExistingHandlersWithNewHandlers() {
        ICapabilityHandler<BaseInvocationContext> newHandler = mock(ICapabilityHandler.class);
        List<ICapabilityHandler<?>> newHandlers = List.of(newHandler);
        Object expectedResult = "new result";

        when(newHandler.canHandle(context)).thenReturn(true);
        when(newHandler.handle(context)).thenReturn(expectedResult);

        dispatcher.setHandlers(newHandlers);
        Object result = dispatcher.dispatch(context);

        assertEquals(expectedResult, result);
        assertEquals(1, dispatcher.getHandlers().size());
        verify(newHandler).handle(context);
        verify(firstHandler, never()).canHandle(any());
        verify(secondHandler, never()).canHandle(any());
    }

    @Test
    @DisplayName("dovrebbe gestire lista vuota di handler")
    void shouldHandleEmptyHandlerList() {
        dispatcher = new CapabilityDispatcher<>(new ArrayList<>());

        IllegalStateException exception = assertThrows(
                IllegalStateException.class,
                () -> dispatcher.dispatch(context)
        );

        assertTrue(exception.getMessage().contains("No handler for"));
    }

    @Test
    @DisplayName("dovrebbe restituire null se l'handler restituisce null")
    void shouldReturnNullIfHandlerReturnsNull() {
        when(firstHandler.canHandle(context)).thenReturn(true);
        when(firstHandler.handle(context)).thenReturn(null);

        Object result = dispatcher.dispatch(context);

        assertNull(result);
        verify(firstHandler).handle(context);
    }

    @Test
    @DisplayName("dovrebbe propagare eccezioni dagli handler")
    void shouldPropagateExceptionsFromHandlers() {
        RuntimeException expectedException = new RuntimeException("handler error");
        when(firstHandler.canHandle(context)).thenReturn(true);
        when(firstHandler.handle(context)).thenThrow(expectedException);

        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> dispatcher.dispatch(context)
        );

        assertEquals(expectedException, exception);
        verify(firstHandler).handle(context);
    }

    @Test
    @DisplayName("dovrebbe svuotare la lista esistente prima di aggiungere nuovi handler")
    void shouldClearExistingListBeforeAddingNewHandlers() {
        ICapabilityHandler<BaseInvocationContext> newHandler = mock(ICapabilityHandler.class);

        dispatcher.setHandlers(List.of(newHandler));

        assertEquals(1, dispatcher.getHandlers().size());
        assertFalse(dispatcher.getHandlers().contains(firstHandler));
        assertFalse(dispatcher.getHandlers().contains(secondHandler));
    }

    @Test
    @DisplayName("dovrebbe mantenere l'ordine degli handler durante la sostituzione")
    void shouldMaintainHandlerOrderDuringReplacement() {
        ICapabilityHandler<BaseInvocationContext> handler1 = mock(ICapabilityHandler.class);
        ICapabilityHandler<BaseInvocationContext> handler2 = mock(ICapabilityHandler.class);
        ICapabilityHandler<BaseInvocationContext> handler3 = mock(ICapabilityHandler.class);

        dispatcher.setHandlers(List.of(handler1, handler2, handler3));

        assertEquals(3, dispatcher.getHandlers().size());
        assertEquals(handler1, dispatcher.getHandlers().get(0));
        assertEquals(handler2, dispatcher.getHandlers().get(1));
        assertEquals(handler3, dispatcher.getHandlers().get(2));
    }

    @Test
    @DisplayName("dovrebbe gestire context null")
    void shouldHandleNullContext() {
        assertThrows(NullPointerException.class, () -> dispatcher.dispatch(null));
    }
}

