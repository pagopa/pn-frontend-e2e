package it.pn.frontend.e2e.framework.annotation.processor.domain_descriptor.loader;


import it.pn.frontend.e2e.framework.annotation.processor.domain_descriptor.parser.DomainDescriptorParser;
import it.pn.frontend.e2e.framework.annotation.processor.domain_descriptor.parser.json.JsonDomainDescriptorParser;
import it.pn.frontend.e2e.framework.annotation.processor.domain_descriptor.validator.DomainDescriptorValidator;
import it.pn.frontend.e2e.framework.annotation.processor.domain_descriptor.validator.factory.DefaultDomainDescriptorValidatorFactory;
import it.pn.frontend.e2e.framework.annotation.processor.domain_descriptor.validator.factory.DomainDescriptorValidatorFactory;
import it.pn.frontend.e2e.framework.core.meta.Descriptor;

import javax.annotation.processing.Messager;
import javax.tools.Diagnostic;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.*;

public final class DescriptorLoader {

    private static final String INDEX_PATH =
            "META-INF/presentation/domains/domains.index";

    private final DomainDescriptorParser parser;
    private final DomainDescriptorValidatorFactory validatorFactory;

    public DescriptorLoader(DomainDescriptorParser parser,
                            DomainDescriptorValidatorFactory validatorFactory) {
        this.parser = parser;
        this.validatorFactory = validatorFactory;
    }

    public static DescriptorLoader defaultLoader() {
        return new DescriptorLoader(
                new JsonDomainDescriptorParser(
                        new com.fasterxml.jackson.databind.ObjectMapper()
                ),
                new DefaultDomainDescriptorValidatorFactory()
        );
    }

    public List<Descriptor> loadAll(ClassLoader classLoader, Messager messager) {
        Map<String, Descriptor> result = new LinkedHashMap<>();

        try {
            Enumeration<URL> indexes = classLoader.getResources(INDEX_PATH);

            if (!indexes.hasMoreElements()) {
                messager.printMessage(
                        Diagnostic.Kind.NOTE,
                        "No domain descriptors found (no " + INDEX_PATH + " present)"
                );
            }

            while (indexes.hasMoreElements()) {
                URL indexUrl = indexes.nextElement();
                for (Descriptor d : loadDescriptorsFromIndex(indexUrl, classLoader, messager)) {
                    Descriptor previous = result.putIfAbsent(d.domainId, d);
                    if (previous != null) {
                        messager.printMessage(
                                Diagnostic.Kind.WARNING,
                                "Duplicate domain descriptor for domainId=" + d.domainId +
                                        " (keeping first occurrence)"
                        );
                    }
                }
            }

        } catch (IOException e) {
            messager.printMessage(
                    Diagnostic.Kind.ERROR,
                    "Failed to load domain descriptors: " + e.getMessage()
            );
        }

        return List.copyOf(result.values());
    }

    private List<Descriptor> loadDescriptorsFromIndex(URL indexUrl, ClassLoader classLoader, Messager messager) {
        List<Descriptor> result = new ArrayList<>();

        try (BufferedReader reader =
                     new BufferedReader(new InputStreamReader(indexUrl.openStream(), StandardCharsets.UTF_8))) {

            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();

                if (line.isEmpty() || line.startsWith("#")) {
                    continue;
                }

                URL descriptorUrl = classLoader.getResource(line);
                if (descriptorUrl == null) {
                    messager.printMessage(
                            Diagnostic.Kind.ERROR,
                            "Domain descriptor not found: " + line
                    );
                    continue;
                }

                Descriptor descriptor = parseAndValidateDescriptor(descriptorUrl, messager);
                if (descriptor != null) {
                    result.add(descriptor);
                }
            }

        } catch (IOException e) {
            messager.printMessage(
                    Diagnostic.Kind.ERROR,
                    "Failed to read domain index " + indexUrl + ": " + e.getMessage()
            );
        }

        return result;
    }

    private Descriptor parseAndValidateDescriptor(URL url, Messager messager) {

        try (InputStream in = url.openStream()) {
            Descriptor descriptor = parser.parse(in);
            DomainDescriptorValidator validator = validatorFactory.forSchema(descriptor.schemaVersion);

            validator.validate(descriptor, messager);
            return descriptor;

        } catch (IllegalStateException e) {
            // validation failed → skip
            return null;

        } catch (Exception e) {
            messager.printMessage(
                    Diagnostic.Kind.ERROR,
                    "Failed to parse domain descriptor " + url + ": " + e.getMessage()
            );
            return null;
        }
    }


}

