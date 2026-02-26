package it.pn.frontend.e2e.framework.core.presentation_binder;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("AbstractPresentationBinder")
class AbstractBinderTest {

    private static class TestBinder extends AbstractBinder {
        @Override
        protected InvocationHandler getInvocationHandler(Class<?> boundType) {
            return (proxy, method, args) -> null;
        }
    }

    private final AbstractBinder binder = new TestBinder();

    @Test
    @DisplayName("dovrebbe creare un proxy per un'interfaccia valida")
    void shouldCreateProxyForValidInterface() {
        TestInterface proxy = binder.bind(TestInterface.class);

        assertTrue(Proxy.isProxyClass(proxy.getClass()));
    }

    @Test
    @DisplayName("dovrebbe lanciare eccezione quando si associa una classe non-interfaccia")
    void shouldThrowExceptionForNonInterfaceClass() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> binder.bind(ConcreteClass.class)
        );

        assertTrue(exception.getMessage().contains("is not an interface"));
    }

    @Test
    @DisplayName("dovrebbe preservare il tipo dell'interfaccia nel proxy")
    void shouldUseSameTypeAsInterface() {
        TestInterface proxy = binder.bind(TestInterface.class);

        assertInstanceOf(TestInterface.class, proxy);
    }

    @Test
    @DisplayName("dovrebbe usare lo stesso classloader dell'interfaccia")
    void shouldUseSameClassLoaderAsInterface() {
        TestInterface proxy = binder.bind(TestInterface.class);
        ClassLoader interfaceClassLoader = TestInterface.class.getClassLoader();

        assertEquals(interfaceClassLoader, proxy.getClass().getClassLoader());
    }

    @Test
    @DisplayName("dovrebbe associare più interfacce indipendentemente")
    void shouldBindMultipleInterfacesIndependently() {
        FirstInterface firstProxy = binder.bind(FirstInterface.class);
        SecondInterface secondProxy = binder.bind(SecondInterface.class);

        assertTrue(Proxy.isProxyClass(firstProxy.getClass()));
        assertTrue(Proxy.isProxyClass(secondProxy.getClass()));
        assertNotEquals(firstProxy.getClass(), secondProxy.getClass());
    }

    @Test
    @DisplayName("dovrebbe gestire interfaccia null")
    void shouldThrowExceptionWhenInterfaceIsNull() {
        assertThrows(NullPointerException.class, () -> binder.bind(null));
    }

    interface TestInterface {
        void testMethod();
    }

    interface FirstInterface {
        void firstMethod();
    }

    interface SecondInterface {
        void secondMethod();
    }

    static class ConcreteClass {
        void concreteMethod() {}
    }
}

