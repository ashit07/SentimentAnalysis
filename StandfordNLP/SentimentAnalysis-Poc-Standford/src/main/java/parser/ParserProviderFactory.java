package parser;

public enum ParserProviderFactory implements ParserProvider {
    csv {
        @Override
        public InputParser getInputParser() {
            return new CSVParser();
        }

        @Override
        public OutputParser getOutputParser() {
            return new CSVParser();
        }
    },
    html {
        @Override
        public InputParser getInputParser() {
            return new HTMLParser();
        }

        @Override
        public OutputParser getOutputParser() {
            return new HTMLParser();
        }
    },
    other {
        @Override
        public InputParser getInputParser() {
            return new UnsupportedParser();
        }

        @Override
        public OutputParser getOutputParser() {
            return new UnsupportedParser();
        }
    }
}
