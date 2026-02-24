package it.pn.frontend.e2e.framework.annotation.processor.domain_descriptor.parser;

import it.pn.frontend.e2e.framework.core.meta.Descriptor;

import java.io.InputStream;

public interface DomainDescriptorParser {
    Descriptor parse(InputStream in) throws Exception;
}
