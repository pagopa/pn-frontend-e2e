package it.pn.frontend.e2e.config;

import io.cucumber.spring.ScenarioScope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.context.annotation.Scope;

import java.util.HashMap;
import java.util.Map;

/**
 * Holds scenario-level state shared between step classes.
 *
 * @ScenarioScope means one fresh instance per scenario —
 * thread-safe and isolated, replacing System.setProperty entirely.
 *
 * ScopedProxyMode.TARGET_CLASS allows it to be injected safely
 * into singleton beans like FrameworkConfig without scope mismatch.
 */
@Component
@ScenarioScope
public class ScenarioContext {

    private final Map<String, String> data = new HashMap<>();
    private final Map<String, Object> objects = new HashMap<>();  // ← add this

    public void set(String key, String value) {
        data.put(key, value);
    }

    public String get(String key) {
        return data.getOrDefault(key, "");
    }

    /**
     * Replaces ${key} placeholders in the given string
     * with values stored in this context.
     */
    public String resolvePlaceholders(String template) {
        String result = template;
        for (Map.Entry<String, String> entry : data.entrySet()) {
            result = result.replace("${" + entry.getKey() + "}", entry.getValue());
        }
        return result;
    }

    // --- Object values (for sharing pages between step classes) ---
    public void setObject(String key, Object value) {
        objects.put(key, value);
    }

    @SuppressWarnings("unchecked")
    public <T> T getObject(String key, Class<T> type) {
        return type.cast(objects.get(key));
    }
}