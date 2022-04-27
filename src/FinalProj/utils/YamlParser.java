package FinalProj.utils;

public class YamlParser {
    private String path;

    public class Data {
        public String profile;
        public int guilt = 0;
        public int resolve = 0;
    }

    YamlParser(String path) {
        this.path = path;
    }
}


