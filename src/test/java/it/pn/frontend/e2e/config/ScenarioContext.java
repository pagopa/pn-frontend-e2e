package it.pn.frontend.e2e.config;

import io.cucumber.spring.ScenarioScope;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@ScenarioScope
public class ScenarioContext {

    private final Map<String, Object> objects = new HashMap<>();  // ← add this

    public void setObject(String key, Object value) {
        objects.put(key, value);
    }

    public <T> T getObject(String key, Class<T> type) {
        return type.cast(objects.get(key));
    }
}