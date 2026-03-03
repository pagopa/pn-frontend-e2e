package it.frontend.e2e.framework.core.capability.dispatcher.handler;

import it.frontend.e2e.framework.core.capability.Capability;
import lombok.Getter;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.concurrent.ConcurrentHashMap;

@Getter
public abstract class AbstractCapabilityHandler<C extends Capability> implements ICapabilityHandler {

    private static final ConcurrentHashMap<Class<?>, Class<?>> GENERIC_TYPE_CACHE = new ConcurrentHashMap<>();

    protected final Class<C> capabilityClass;

    protected AbstractCapabilityHandler() {
        this.capabilityClass = extractGenericCapabilityType();
    }

    @Override
    public boolean canHandle(Method method) {
        if (method == null) {
            return false;
        }

        Class<?> declaringClass = method.getDeclaringClass();
        return isCapabilityCompatible(declaringClass);
    }

    private boolean isCapabilityCompatible(Class<?> declaringClass) {
        if (declaringClass == null) {
            return false;
        }

        // Case 1: La classe dichiarante è la capability stessa o una sua sottointerfaccia
        if (capabilityClass.isAssignableFrom(declaringClass)) {
            return true;
        }

        // Case 2: La classe dichiarante è una superinterfaccia della capability
        // (utile per handler di capability base che devono gestire anche sottoclassi)
        return declaringClass.isAssignableFrom(capabilityClass);
    }

    @SuppressWarnings("unchecked")
    private Class<C> extractGenericCapabilityType() {
        Class<?> subclass = this.getClass();

        // Verifica cache prima
        Class<?> cached = GENERIC_TYPE_CACHE.get(subclass);
        if (cached != null) {
            return (Class<C>) cached;
        }

        try {
            Type genericSuperclass = subclass.getGenericSuperclass();

            if (!(genericSuperclass instanceof ParameterizedType parameterizedType)) {
                throw new IllegalStateException(
                    "Impossibile estrarre il tipo generico da " + subclass.getName() +
                    ". Assicurati che estenda direttamente AbstractCapabilityHandler<C>"
                );
            }

            Type[] typeArguments = parameterizedType.getActualTypeArguments();

            if (typeArguments.length == 0 || !(typeArguments[0] instanceof Class)) {
                throw new IllegalStateException(
                    "Impossibile determinare il parametro di tipo generico per " + subclass.getName()
                );
            }

            Class<C> result = (Class<C>) typeArguments[0];

            // Valida che il tipo sia una Capability
            if (!Capability.class.isAssignableFrom(result)) {
                throw new IllegalStateException(
                    "Il tipo generico " + result.getName() + " non è una Capability. " +
                    "Assicurati che estenda l'interfaccia Capability"
                );
            }

            // Metti in cache per performance
            GENERIC_TYPE_CACHE.put(subclass, result);
            return result;

        } catch (Exception e) {
            throw new IllegalStateException(
                "Errore durante l'estrazione del tipo generico per " + subclass.getName(), e
            );
        }
    }

}
