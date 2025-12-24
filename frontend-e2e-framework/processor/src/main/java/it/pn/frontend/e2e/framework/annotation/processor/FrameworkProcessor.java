package it.pn.frontend.e2e.framework.annotation.processor;

import com.google.auto.service.AutoService;
import it.pn.frontend.e2e.framework.annotation.processor.loader.DescriptorLoader;
import it.pn.frontend.e2e.framework.annotation.processor.loader.ScopeResolver;
import it.pn.frontend.e2e.framework.annotation.processor.rule.engine.RuleEngine;
import it.pn.frontend.e2e.framework.core.domain.descriptor.ConstraintDescriptor;
import it.pn.frontend.e2e.framework.core.domain.descriptor.DomainDescriptor;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.TypeElement;
import java.util.List;
import java.util.Set;

@AutoService(Processor.class)
@SupportedAnnotationTypes("*")
@SupportedSourceVersion(SourceVersion.RELEASE_17)
public final class FrameworkProcessor extends AbstractProcessor {

    private List<DomainDescriptor> domains;
    private DescriptorLoader descriptorLoader;

    @Override
    public synchronized void init(ProcessingEnvironment env) {
        super.init(env);

        this.descriptorLoader = new DescriptorLoader();
        this.domains = descriptorLoader.loadAll(
                getClass().getClassLoader(),
                env.getMessager()
        );
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {

        if (roundEnv.processingOver()) {
            return false;
        }

        ScopeResolver scopeResolver =
                new ScopeResolver(processingEnv);

        RuleEngine ruleEngine =
                new RuleEngine(processingEnv);

        for (DomainDescriptor domain : domains) {
            for (ConstraintDescriptor constraint : domain.constraints) {

                Set<TypeElement> targets =
                        scopeResolver.resolve(
                                domain,
                                constraint.scope,
                                roundEnv
                        );

                if (constraint.mustHaveAnnotation != null) {
                    ruleEngine.applyMustHaveAnnotation(
                            constraint,
                            targets
                    );
                }
            }
        }

        return false;
    }

}


