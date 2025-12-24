package it.pn.frontend.e2e.framework.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import java.lang.annotation.*;

/**
 * Marks a DomNode selector method and declares behavior capabilities
 * to be merged into the generated return type.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.SOURCE) // keep it for runtime usage too (binder can read it)
@Documented
public @interface Selector {

    /** CSS/XPath/etc selector string (domain-specific) */
    String value();

    /**
     * Capability interfaces whose methods should become available on the return type.
     * Must be interfaces.
     */
    Class<?>[] capabilities() default {};
}



