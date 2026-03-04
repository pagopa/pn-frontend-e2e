package it.frontend.e2e.framework.core.capability.context;

import java.util.ArrayDeque;
import java.util.Deque;

public class CapabilityContext {
    private CapabilityContext() {}

    private static final ThreadLocal<Deque<String>> SELECTOR_STACK =
            ThreadLocal.withInitial(ArrayDeque::new);

    public static void pushSelector(String selector) {
        SELECTOR_STACK.get().push(selector);
    }

    public static String selector() {
        return SELECTOR_STACK.get().peek();
    }

    public static void popSelector() {
        Deque<String> d = SELECTOR_STACK.get();
        d.pop();
        if (d.isEmpty()) {
            SELECTOR_STACK.remove();
        }
    }
}
