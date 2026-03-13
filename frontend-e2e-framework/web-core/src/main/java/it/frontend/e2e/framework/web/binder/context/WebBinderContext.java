package it.frontend.e2e.framework.web.binder.context;

import it.frontend.e2e.framework.core.binder.context.BindContext;
import lombok.Getter;

@Getter
public class WebBinderContext extends BindContext {
    private final String url;

    public WebBinderContext(String selector, String url) {
        super(selector);
        this.url = url;
    }

    public WebBinderContext(String selector) {
        super(selector);
        this.url = "";
    }

    public static WebBinderContext root(String url) {
        return new WebBinderContext("", url);
    }

    @Override
    public String toString() {
        String parent = super.toString();
        String currentUrl = (url == null) ? "" : url.trim();

        if (currentUrl.isEmpty()) {
            return parent;
        }
        if (parent == null || parent.isBlank()) {
            return "URL: " + currentUrl;
        }
        return parent + ", URL: " + currentUrl;
    }
}
