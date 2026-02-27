package it.frontend.e2e.framework.core.capability.handler;

import it.frontend.e2e.framework.core.capability.Gettable;
import it.frontend.e2e.framework.core.model.TestElement;
import it.frontend.e2e.framework.core.model.TestLocation;
import it.frontend.e2e.framework.core.model.TestSelector;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("AbstractCapabilityHandler")
class AbstractCapabilityHandlerTest {

    private interface TestCapability extends Gettable<TestSelector, TestLocation, TestElement> {
        void action();
        void anotherAction();
    }

    private interface DifferentCapability extends Gettable<TestSelector, TestLocation, TestElement> {
        void differentAction();
    }

    private static class TestCapabilityHandler extends AbstractCapabilityHandler<TestSelector, TestLocation, TestElement, TestCapability> {

        protected TestCapabilityHandler() {
            super(TestCapability.class);
        }

        @Override
        public <T> T handle(Method method) {
            return null;
        }
    }

    private static class DifferentCapabilityHandler extends AbstractCapabilityHandler<TestSelector, TestLocation, TestElement, DifferentCapability> {

        protected DifferentCapabilityHandler() {
            super(DifferentCapability.class);
        }

        @Override
        public <T> T handle(Method method) {
            return null;
        }
    }

    private TestCapabilityHandler handler;

    @Test
    @DisplayName("dovrebbe riconoscere un metodo della capability dichiarata")
    void shouldRecognizeMethodFromDeclaredCapability() throws NoSuchMethodException {
        handler = new TestCapabilityHandler();
        Method method = TestCapability.class.getMethod("action");

        assertTrue(handler.canHandle(method));
    }

    @Test
    @DisplayName("dovrebbe riconoscere un metodo diverso della stessa capability")
    void shouldRecognizeAnyMethodFromDeclaredCapability() throws NoSuchMethodException {
        handler = new TestCapabilityHandler();
        Method method = TestCapability.class.getMethod("anotherAction");

        assertTrue(handler.canHandle(method));
    }

    @Test
    @DisplayName("dovrebbe rifiutare un metodo di una capability diversa")
    void shouldRejectMethodFromDifferentCapability() throws NoSuchMethodException {
        handler = new TestCapabilityHandler();
        Method method = DifferentCapability.class.getMethod("differentAction");

        assertFalse(handler.canHandle(method));
    }

    @Test
    @DisplayName("dovrebbe rifiutare metodo null")
    void shouldThrowExceptionForNullMethod() {
        handler = new TestCapabilityHandler();

        assertThrows(Exception.class, () -> {
            Method nullMethod = null;
            handler.canHandle(nullMethod);
        });
    }

    @Test
    @DisplayName("dovrebbe distinguere tra handler per capability diverse")
    void shouldDistinguishBetweenDifferentCapabilityHandlers() throws NoSuchMethodException {
        TestCapabilityHandler firstHandler = new TestCapabilityHandler();
        DifferentCapabilityHandler secondHandler = new DifferentCapabilityHandler();
        Method testCapabilityMethod = TestCapability.class.getMethod("action");
        Method differentCapabilityMethod = DifferentCapability.class.getMethod("differentAction");

        assertTrue(firstHandler.canHandle(testCapabilityMethod));
        assertFalse(firstHandler.canHandle(differentCapabilityMethod));
        assertFalse(secondHandler.canHandle(testCapabilityMethod));
        assertTrue(secondHandler.canHandle(differentCapabilityMethod));
    }

    @Test
    @DisplayName("dovrebbe gestire correttamente handler per la stessa capability")
    void shouldHandleMultipleHandlersForSameCapability() throws NoSuchMethodException {
        TestCapabilityHandler handler1 = new TestCapabilityHandler();
        TestCapabilityHandler handler2 = new TestCapabilityHandler();
        Method method = TestCapability.class.getMethod("action");

        assertTrue(handler1.canHandle(method));
        assertTrue(handler2.canHandle(method));
    }

    @Test
    @DisplayName("dovrebbe usare l'uguaglianza della classe per determinare se può gestire")
    void shouldUseExactClassEqualityForHandling() throws NoSuchMethodException {
        handler = new TestCapabilityHandler();
        Method method = TestCapability.class.getMethod("action");
        Class<?> methodDeclaringClass = method.getDeclaringClass();
        Class<?> expectedCapabilityClass = TestCapability.class;

        assertEquals(expectedCapabilityClass, methodDeclaringClass);
        assertTrue(handler.canHandle(method));
    }

}






