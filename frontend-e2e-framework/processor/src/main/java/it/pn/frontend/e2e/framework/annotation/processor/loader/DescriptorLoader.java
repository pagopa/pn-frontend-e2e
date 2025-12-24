package it.pn.frontend.e2e.framework.annotation.processor.loader;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.pn.frontend.e2e.framework.core.domain.descriptor.DomainDescriptor;

import javax.annotation.processing.Messager;
import javax.tools.Diagnostic;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public final class DescriptorLoader {

    private static final String INDEX_PATH =
            "META-INF/presentation/domains/domains.index";

    private final ObjectMapper mapper = new ObjectMapper();

    public List<DomainDescriptor> loadAll(ClassLoader classLoader, Messager messager) {
        List<DomainDescriptor> result = new ArrayList<>();

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
                result.addAll(loadFromIndex(indexUrl, classLoader, messager));
            }

        } catch (IOException e) {
            messager.printMessage(
                    Diagnostic.Kind.ERROR,
                    "Failed to load domain descriptors: " + e.getMessage()
            );
        }

        return result;
    }

    private List<DomainDescriptor> loadFromIndex(
            URL indexUrl,
            ClassLoader classLoader,
            Messager messager
    ) {
        List<DomainDescriptor> result = new ArrayList<>();

        try (BufferedReader reader =
                     new BufferedReader(new InputStreamReader(indexUrl.openStream()))) {

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

                DomainDescriptor descriptor = parseDescriptor(descriptorUrl, messager);
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

    private DomainDescriptor parseDescriptor(URL url, Messager messager) {
        try (InputStream in = url.openStream()) {
            DomainDescriptor descriptor =
                    mapper.readValue(in, DomainDescriptor.class);

            validateDescriptor(descriptor, messager);
            return descriptor;

        } catch (Exception e) {
            messager.printMessage(
                    Diagnostic.Kind.ERROR,
                    "Failed to parse domain descriptor " + url + ": " + e.getMessage()
            );
            return null;
        }
    }

    private void validateDescriptor(DomainDescriptor d, Messager messager) {
        if (d.schemaVersion != 1) {
            messager.printMessage(
                    Diagnostic.Kind.ERROR,
                    "Unsupported schemaVersion " + d.schemaVersion +
                            " in domain " + d.domainId
            );
        }

        if (d.domainId == null || d.domainId.isBlank()) {
            messager.printMessage(
                    Diagnostic.Kind.ERROR,
                    "domainId is missing in domain descriptor"
            );
        }

        if (d.scopes == null || d.scopes.isEmpty()) {
            messager.printMessage(
                    Diagnostic.Kind.WARNING,
                    "No scopes defined for domain " + d.domainId
            );
        }

        if (d.constraints == null || d.constraints.isEmpty()) {
            messager.printMessage(
                    Diagnostic.Kind.NOTE,
                    "No constraints defined for domain " + d.domainId
            );
        }
    }
}

