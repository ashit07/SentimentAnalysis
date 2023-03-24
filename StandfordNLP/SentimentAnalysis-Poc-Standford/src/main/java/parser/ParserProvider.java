package parser;

import parser.InputParser;

public interface ParserProvider {
    public InputParser getInputParser();
    public OutputParser getOutputParser();
}
