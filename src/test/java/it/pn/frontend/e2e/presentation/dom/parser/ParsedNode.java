package it.pn.frontend.e2e.presentation.dom.parser;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
public class ParsedNode {
    private String tag;
    private String text;
    private Map<String, String> attributes;
    private List<ParsedNode> children;
}

