package it.pn.frontend.e2e.framework.annotation.processor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.auto.service.AutoService;
import it.pn.frontend.e2e.framework.annotation.processor.domain.descriptor.loader.DescriptorLoader;
import it.pn.frontend.e2e.framework.annotation.processor.domain.descriptor.parser.json.JsonDomainDescriptorParser;
import it.pn.frontend.e2e.framework.annotation.processor.domain.descriptor.validator.factory.DefaultDomainDescriptorValidatorFactory;
import it.pn.frontend.e2e.framework.annotation.processor.domain.descriptor.resolution.scope.ScopeResolver;
import it.pn.frontend.e2e.framework.annotation.processor.rule.engine.RuleEngine;
import it.pn.frontend.e2e.framework.core.domain.descriptor.ConstraintDescriptor;
import it.pn.frontend.e2e.framework.core.domain.descriptor.DomainDescriptor;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.TypeElement;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@AutoService(Processor.class)
@SupportedAnnotationTypes("*")
@SupportedSourceVersion(SourceVersion.RELEASE_17)
public final class FrameworkProcessor extends AbstractProcessor {

    private List<DomainDescriptor> domains = Collections.emptyList();

    @Override
    public synchronized void init(ProcessingEnvironment env) {
        super.init(env);

        DescriptorLoader descriptorLoader =
                new DescriptorLoader(
                        new JsonDomainDescriptorParser(new ObjectMapper()),
                        new DefaultDomainDescriptorValidatorFactory()
                );

        this.domains = descriptorLoader.loadAll(
                getClass().getClassLoader(),
                env.getMessager()
        );
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {

        if (roundEnv.processingOver() || domains.isEmpty())
            return false;

        ScopeResolver scopeResolver = new ScopeResolver(processingEnv);
        RuleEngine ruleEngine = new RuleEngine(processingEnv);

        for (DomainDescriptor domain : domains) {

            if (domain.constraints == null || domain.constraints.isEmpty())
                continue;

            for (ConstraintDescriptor constraint : domain.constraints) {
                Set<TypeElement> targets = scopeResolver.resolve(domain, constraint.scope, roundEnv);

                if (constraint.mustHaveAnnotation != null)
                    ruleEngine.applyMustHaveAnnotation(constraint, targets);
            }
        }

        return false;
    }
}
