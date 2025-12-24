package it.pn.frontend.e2e.framework.annotation.processor.domain.descriptor.parser.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.pn.frontend.e2e.framework.annotation.processor.domain.descriptor.parser.DomainDescriptorParser;
import it.pn.frontend.e2e.framework.core.domain.descriptor.DomainDescriptor;

import java.io.InputStream;

public final class JsonDomainDescriptorParser implements DomainDescriptorParser {

    private final ObjectMapper mapper;

    public JsonDomainDescriptorParser(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public DomainDescriptor parse(InputStream in) throws Exception {
        return mapper.readValue(in, DomainDescriptor.class);
    }
}
