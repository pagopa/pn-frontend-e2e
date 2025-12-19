package it.pn.frontend.e2e.presentation.dom.parser;

public interface IDOMParser<T> {
     ParsedNode parse(T element);
}