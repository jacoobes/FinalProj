package FinalProj.utils;

import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


public class YamlParser {

    /**
     * Using external library : SnakeYaml
     */
    private final Yaml yaml = new Yaml(new Constructor(Data.class));
    private List<Data> profiles = new ArrayList<>();
    public static class Data implements Cloneable
    {
        public String name;
        public String savedScene;
        public int guilt = 0;
        public int resolve = 0;
        public ArrayList<String> choices = new ArrayList<>();
        public String _fp;
        @Override
        public String toString() {
            return "Data{" +
                    "name='" + name + '\'' +
                    ", savedScene='" + savedScene + '\'' +
                    ", guilt=" + guilt +
                    ", resolve=" + resolve +
                    ", choices=" + choices +
                    ", _fp=" + _fp +
                    '}';
        }

        public void addGuilt(int guilt) {
            this.guilt += guilt;
        }
        public void addChoice(String choice) { choices.add(choice); }
        public void addResolve(int resolve) {
            this.resolve += resolve;
        }

        public Data clone() throws CloneNotSupportedException {
            return (Data) super.clone();
        }
    }

    public Data[] getProfiles()
    {
        return profiles.toArray(new Data[3]);
    }

    YamlParser(String path) {
        File dir = new File(path);
        File[] files = dir.listFiles();
        if(files == null)
        {
            System.out.println("Cannot find profile directory");
            return;
        }
        profiles = Arrays.stream(dir.listFiles())
                 .map(f -> {
                     try
                     {
                         return (Data) yaml.load(new FileInputStream(f));
                     } catch (FileNotFoundException e)
                     {
                         e.printStackTrace();
                     }
                     return null;
                 }).collect(Collectors.toList());


    }
    public void dump(Data d) throws IOException {
        yaml.dump(d, new FileWriter(d._fp));
    }

}


