package it.pn.frontend.e2e.framework.annotation.processor.domain.descriptor.parser;

import it.pn.frontend.e2e.framework.core.domain.descriptor.DomainDescriptor;

import java.io.InputStream;

public interface DomainDescriptorParser {
    DomainDescriptor parse(InputStream in) throws Exception;
}
