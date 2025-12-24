package it.pn.frontend.e2e.framework.annotation.processor.loader;

import it.pn.frontend.e2e.framework.core.domain.descriptor.DomainDescriptor;
import it.pn.frontend.e2e.framework.core.domain.descriptor.ScopeDescriptor;

import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import javax.tools.Diagnostic;
import java.util.HashSet;
import java.util.Set;

public final class ScopeResolver {

    private final Types types;
    private final Elements elements;
    private final Messager messager;

    public ScopeResolver(ProcessingEnvironment env) {
        this.types = env.getTypeUtils();
        this.elements = env.getElementUtils();
        this.messager = env.getMessager();
    }

    public Set<TypeElement> resolve(DomainDescriptor domain, String scopeName, RoundEnvironment roundEnv) {
        ScopeDescriptor scope = domain.scopes.get(scopeName);

        if (scope == null) {
            messager.printMessage(
                    Diagnostic.Kind.ERROR,
                    "Unknown scope '" + scopeName + "' in domain '" + domain.domainId + "'"
            );
            return Set.of();
        }

        if (scope.subtypesOf != null) {
            return resolveSubtypesOf(scope.subtypesOf, roundEnv);
        }

        messager.printMessage(
                Diagnostic.Kind.ERROR,
                "Unsupported scope definition for scope '" + scopeName + "' in domain '" + domain.domainId + "'"
        );
        return Set.of();
    }

    private Set<TypeElement> resolveSubtypesOf(String targetFqcn, RoundEnvironment roundEnv) {
        TypeElement targetType = elements.getTypeElement(targetFqcn);

        if (targetType == null) {
            messager.printMessage(
                    Diagnostic.Kind.ERROR,
                    "Scope target type not found: " + targetFqcn
            );
            return Set.of();
        }

        Set<TypeElement> result = new HashSet<>();

        for (Element root : roundEnv.getRootElements()) {

            if (!(root instanceof TypeElement candidate)) {
                continue;
            }

            // SOLO interfacce
            if (candidate.getKind() != ElementKind.INTERFACE) {
                continue;
            }

            // Escludi l'interfaccia target stessa
            if (types.isSameType(candidate.asType(), targetType.asType())) {
                continue;
            }

            // candidate estende (direttamente o indirettamente) targetType
            if (types.isAssignable(candidate.asType(), targetType.asType())) {
                result.add(candidate);
            }
        }

        return result;
    }

}



